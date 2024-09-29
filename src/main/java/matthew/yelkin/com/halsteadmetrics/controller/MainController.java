package matthew.yelkin.com.halsteadmetrics.controller;

import lombok.RequiredArgsConstructor;
import matthew.yelkin.com.halsteadmetrics.service.TokenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final TokenService tokenService;

    @GetMapping()
    public String getMainPage() {
        return "halsteadMetrics";
    }

    @PostMapping()
    public String getMainPage(@RequestParam(value = "code") String code, Model model) {
        tokenService.analyzeCode(code);

        model.addAttribute("code", code);
        model.addAttribute("operands", tokenService.getOperands()
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()));
        model.addAttribute("operators", tokenService.getOperators()
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()));
        model.addAttribute("metrics", tokenService.getMetrics());

        return getMainPage();
    }
}
