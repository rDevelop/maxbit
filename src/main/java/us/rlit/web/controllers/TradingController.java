package us.rlit.web.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import us.rlit.api.models.itbits.ItBitWallet;
import us.rlit.api.services.trading.WalletService;

/**
 * Created by rob on 11/19/16.
 */
@Controller
public class TradingController implements ErrorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WalletService walletService;

    @Autowired
    public void setWalletService(WalletService walletService) {
        this.walletService = walletService;
    }

    @RequestMapping("/trading")
    public String index(Model model) {
        logger.info("/trading");
        model.addAttribute("message", "Welcome");
        model.addAttribute("fragment", "welcome");
        return "trading/index";
    }

    @RequestMapping("/trading/login")
    public String login(Model model) {
        logger.info("/trading/login");
        model.addAttribute("fragment", "login");
        return "trading";
    }


    @RequestMapping(value = "/trading/wallets", method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("/trading/wallets");
        Iterable<ItBitWallet> itBitAccountInfo = walletService.getAllWallets();
        if(itBitAccountInfo != null ) {
            model.addAttribute("wallets", itBitAccountInfo);
            return "trading/wallets";
        }
        return showError(model, "Error getting wallets", walletService);
    }

    @RequestMapping("/trading/wallets/{id}")
    public String showWallet(@PathVariable String id, Model model) {
        logger.info("/trading/wallets{{}}", id);
        ItBitWallet itBitWallet = walletService.getWalletById(id);
        if( itBitWallet != null) {
            model.addAttribute("wallet", itBitWallet);
            return "trading/walletshow";
        }
        return showError(model, "Error getting wallet: " + id, walletService);
    }

    @RequestMapping("/trading/wallets/reset")
    public String resetWallets(Model model) {
        logger.info("/trading/wallets/reset");
        walletService.refreshWallets();
        Iterable<ItBitWallet> itBitAccountInfo = walletService.getAllWallets();
        if(itBitAccountInfo != null ) {
            model.addAttribute("wallets", itBitAccountInfo);
            return "trading/wallets";
        }
        return showError(model, "Error getting wallets", walletService);
    }

    @RequestMapping("/trading/wallet/{id}")
    public String getWallet(@PathVariable String id, Model model) {
        logger.info("/trading/wallet{{}}", id);
        ItBitWallet itBitWallet = walletService.getWallet(id);
        if(itBitWallet != null) {
            model.addAttribute("wallet", itBitWallet);
            return "trading/walletshow";
        }
        return showError(model, "Error getting wallet", walletService);

    }

    @RequestMapping("/trading/wallet/balance/{id}/{currencyCode}")
    public String getBalance(@PathVariable String id, @PathVariable String currencyCode, Model model) {
        logger.info("/trading/wallet/balance/{{}}/{{}}", id, currencyCode);
        ItBitWallet itBitWallet = walletService.getBalance(id, currencyCode);
        if(itBitWallet != null) {
            model.addAttribute("wallet", itBitWallet);
            return "trading/walletshow";
        }
        return showError(model, "Error getting balance", walletService);

    }

    /**
     * showError returns a response error to the error page
     * @param model
     * @param error
     * @return Template page
     */
//    String showError(Model model, String error, BaseService walletService) {
//        MaxResponseErrorHandler responseError = walletService.getMaxResponseError();
//        model.addAttribute("responseError", responseError);
//        model.addAttribute("reason", error);
//        return "trading/response-error";
//    }

}

//bbbcfc7e-bd6f-4f56-b61c-7192743b7099
