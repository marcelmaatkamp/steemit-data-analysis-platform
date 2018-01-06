package application.utils;

import eu.bittrade.libs.steemj.base.models.SignedBlockHeader;
import eu.bittrade.libs.steemj.communication.BlockAppliedCallback;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomCallback extends BlockAppliedCallback {
    @Override
    public void onNewBlock(SignedBlockHeader signedBlockHeader) {
        System.out.println("Signed block addition: " + signedBlockHeader.toString());
    }
}
