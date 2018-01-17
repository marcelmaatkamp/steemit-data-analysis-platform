package application.steemj;

import application.Application;
import eu.bittrade.libs.steemj.SteemJ;
import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.base.models.Permlink;
import eu.bittrade.libs.steemj.base.models.operations.CommentOperation;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemInvalidTransactionException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@Slf4j
public class TestSteemj {

    @Autowired
    SteemJ steemJ;

    @Test
    public void testPost() {
        log.info("testpost");

        CommentOperation commentOperation;
        try {
            // https://steemit.com/test/@steemj/testofsteemj040
            commentOperation = steemJ.createComment(new AccountName("steemj"), new Permlink("testofsteemj040"),
                    "Example comment without no link but with a @user .", new String[]{"test"});
            assertEquals("testofsteemj040", commentOperation.getParentPermlink().getLink());
            steemJ.deletePostOrComment(commentOperation.getPermlink());
        } catch (SteemCommunicationException e) {
            log.error("SteemCommunicationException", e);
        } catch (SteemResponseException e) {
            log.error("SteemResponseException", e);
        } catch (SteemInvalidTransactionException e) {
            log.error("SteemInvalidTransactionException", e);
        }
    }

}