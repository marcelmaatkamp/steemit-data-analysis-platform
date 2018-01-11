package application.configuration.steemit;

import eu.bittrade.libs.steemj.SteemJ;
import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.base.models.SignedBlockHeader;
import eu.bittrade.libs.steemj.communication.BlockAppliedCallback;
import eu.bittrade.libs.steemj.configuration.SteemJConfig;
import eu.bittrade.libs.steemj.enums.PrivateKeyType;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class SteemitConfiguration {


    @Value("${steemit.account.name}")
    String accountName;

    @Value("${steemit.account.postkey}")
    String postKey;

    @Value("${steemit.account.activekey}")
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
        steemJ.setBlockAppliedCallback(blockAppliedCallback());
        return steemJ;
    }
}