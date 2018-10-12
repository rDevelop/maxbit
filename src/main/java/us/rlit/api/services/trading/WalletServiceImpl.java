package us.rlit.api.services.trading;

import org.springframework.stereotype.Service;
import us.rlit.api.models.itbits.ItBitWallet;
import us.rlit.api.models.MaxResponseErrorHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    private Wallets wallets = new Wallets();
    private List<ItBitWallet> itBitAccountInfoList;

    @Override
    public List<ItBitWallet> getAllWallets() {
        if (itBitAccountInfoList == null) {
            itBitAccountInfoList = new ArrayList<>();
        }
        if (itBitAccountInfoList.isEmpty()) {
            itBitAccountInfoList = new Wallets().getAllWallets();
        }
        return itBitAccountInfoList;
    }

    @Override
    public ItBitWallet getWalletById(String id) {
        if (itBitAccountInfoList != null) {
            itBitAccountInfoList = getAllWallets();
            Optional<ItBitWallet> account = itBitAccountInfoList.stream()
                    .filter(w -> w.getId().equals(id)).findFirst();

            if (account.isPresent()) {
                ItBitWallet wallet = account.get();
                return wallet;
            }
        }
        return null;
    }

    @Override
    public ItBitWallet getWallet(String id) {
        return wallets.getWallet(id);
    }

    @Override
    public void refreshWallets() {
        itBitAccountInfoList = null;
    }

    @Override
    public ItBitWallet getBalance(String id, String currencyCode) {
        return wallets.getBalance(id, currencyCode);
    }

    @Override
    public MaxResponseErrorHandler getMaxResponseError() {
        return wallets.getMaxResponseError();
    }


}
