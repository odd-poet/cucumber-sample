package net.oddpoet.cucumber.sample.testsupport;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: oddpoet
 * Date: 13. 5. 29.
 * Time: 오전 1:14
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-test-config.xml")
@Ignore
public class MockBaseTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
}
