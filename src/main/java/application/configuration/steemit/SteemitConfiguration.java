package application.configuration.steemit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import application.utils.CustomCallback;
import eu.bittrade.libs.steemj.SteemJ;
import eu.bittrade.libs.steemj.apis.market.history.model.MarketTicker;
import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.base.models.SignedBlockHeader;
import eu.bittrade.libs.steemj.communication.BlockAppliedCallback;
import eu.bittrade.libs.steemj.configuration.SteemJConfig;
import eu.bittrade.libs.steemj.enums.PrivateKeyType;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SteemitConfiguration { 

    @Value("${steemit.account.name}")
    String accountName;

    @Value("${steemit.account.key.post}")
    String postKey;

    @Value("${steemit.account.key.active}")
    String activeKey;

    @Bean
    SteemJConfig SteemJConfig() { 

        log.info("account("+accountName+"), postKey("+postKey+"), activeKey("+activeKey+")");
        
        SteemJConfig steemJConfig = SteemJConfig.getInstance();
        steemJConfig.setResponseTimeout(100000);
        steemJConfig.setDefaultAccount(new AccountName(accountName));

        List<ImmutablePair<PrivateKeyType, String>> privateKeys = new ArrayList<>();
        privateKeys.add(new ImmutablePair<>(PrivateKeyType.POSTING, postKey));
        privateKeys.add(new ImmutablePair<>(PrivateKeyType.ACTIVE, activeKey));
            
        steemJConfig.getPrivateKeyStorage().addAccount(steemJConfig.getDefaultAccount(), privateKeys);

        return steemJConfig;
    }

    @Bean
    BlockAppliedCallback blockAppliedCallback() { 
        BlockAppliedCallback blockAppliedCallback = new BlockAppliedCallback(){
            @Override
            public void onNewBlock(SignedBlockHeader signedBlockHeader) {
                log.info("onNewBlock: " + signedBlockHeader.toString());
                System.out.println(signedBlockHeader.toString());
            }
        };

        return blockAppliedCallback;
    }

    @Bean
    SteemJ steemj() throws SteemCommunicationException, SteemResponseException { 
        SteemJ steemJ = new SteemJ();
        // steemJ.setBlockAppliedCallback(blockAppliedCallback());
        steemJ.setBlockAppliedCallback(new CustomCallback());

        log.info(("HF version fetched: " + steemJ.getHardforkVersion()));

        MarketTicker marketTicker = steemJ.getTicker();
        log.info("Market ticker: " + marketTicker.toString());

        log.info("block: " + steemJ.getBlock(1));

        return steemJ;
    }
}