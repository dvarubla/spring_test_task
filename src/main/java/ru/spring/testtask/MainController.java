package ru.spring.testtask;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spring.testtask.dao.PurchaseRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@Controller
@RequestMapping("/")
public class MainController {
    private PurchaseRepository _purchaseRepository;

    MainController(PurchaseRepository purchaseRepository){
        _purchaseRepository = purchaseRepository;
    }

    @GetMapping
    public String getRoot(Optional<Integer> num, Model model){
        if(num.isPresent()) {
            final BigDecimal[] totalPurchase = {new BigDecimal(0)};
            List<PurchaseInfo> purchaseList =
                    _purchaseRepository.findByNum(num.get())
                            .stream().map(p -> {
                                BigDecimal total = p.getProduct().getPrice().multiply(BigDecimal.valueOf(p.getCount()));
                                totalPurchase[0] = totalPurchase[0].add(total);
                                return new PurchaseInfo(
                                    p.getProduct().getName(), p.getCount(), p.getProduct().getPrice(), total
                                );
                            }
                    ).collect(Collectors.toList());
            model.addAttribute("total", totalPurchase);
            model.addAttribute("purchases", purchaseList);
        }
        return "index.html";
    }
}
