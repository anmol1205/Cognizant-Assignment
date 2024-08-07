package example;


import org.springframework.context.support.ClassPathXmlApplicationContext;
public class HelloWorld {
	
	public static void main(String[] args) {
 
		// loading the Bean and XML definitions from the given XML file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloWorldService obj = context.getBean(HelloWorldService.class);
		obj.hello();
		context.close();
	}
}
