package ru.spring.testtask;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spring.testtask.dao.PurchaseRepository;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainController {
    private PurchaseRepository _purchaseRepository;
    private UIntValidator _uIntValidator;

    MainController(PurchaseRepository purchaseRepository, UIntValidator validator){
        _purchaseRepository = purchaseRepository;
        _uIntValidator = validator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(_uIntValidator);
    }

    @GetMapping
    public String getRoot(@ModelAttribute("num") @Valid String numStr, BindingResult bindingResult, Model model){
        if(numStr.length() != 0) {
            if(!bindingResult.hasErrors()) {
                int num = Integer.valueOf(numStr);
                final BigDecimal[] totalPurchase = {new BigDecimal(0)};
                List<PurchaseInfo> purchaseList =
                        _purchaseRepository.findByNum(num)
                                .stream().map(p -> {
                                    BigDecimal total = p.getProduct().getPrice().multiply(BigDecimal.valueOf(p.getCount()));
                                    totalPurchase[0] = totalPurchase[0].add(total);
                                    return new PurchaseInfo(
                                            p.getProduct().getName(), p.getCount(), p.getProduct().getPrice(), total
                                    );
                                }
                        ).collect(Collectors.toList());
                model.addAttribute("purchaseNum", num);
                model.addAttribute("total", totalPurchase);
                model.addAttribute("purchases", purchaseList);
            }
        }
        return "index.html";
    }
}
