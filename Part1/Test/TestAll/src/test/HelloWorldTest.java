import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class HelloWorldTest {
	private HelloWorld helloworld = new HelloWorld();

	@Test
	public void testStr() {
		helloworld.hello();
		assertEquals("Hello World", helloworld.getStr());
	}
}