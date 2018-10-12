package us.rlit.api.services.trading;


import us.rlit.api.services.BaseService;
import us.rlit.api.models.itbits.ItBitWallet;
import us.rlit.api.models.MaxResponseErrorHandler;

public interface WalletService extends BaseService {

    Iterable<ItBitWallet> getAllWallets();

    ItBitWallet getWalletById(String id);

    void refreshWallets();

    ItBitWallet getWallet(String id);

    ItBitWallet getBalance(String id, String currencyCode);



    MaxResponseErrorHandler getMaxResponseError();

}
