import static org.junit.Assert.*;
import org.junit.Test;

public class HelloWorldTest {
	public HelloWorld hellowolrd = new HelloWorld();

	@Test
	public void testHello() {
		hellowolrd.hello();
		assertEquals("Hello World!", hellowolrd.getStr());
	}
}