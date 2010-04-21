import java.sql.SQLException;

import org.hibernate.validator.InvalidStateException;
import org.hibernate.validator.InvalidValue;
import org.junit.Before;
import org.junit.Test;

import com.dhev.ExpressionLanguageResolverFactory;
import com.dhev.test.TestResolver;
import com.dhev.test.TestSchema;

public class MyTest {

	@Before
	public void before() {
		TestSchema.config.addAnnotatedClass(User.class);
	}

	@Test
	public void test() throws SQLException, ClassNotFoundException {

		ExpressionLanguageResolverFactory.registerResolver(new TestResolver());

		User user = new User();
		user.setName("sd");
		user.setSurname("xy");
		user.setTest(new String[] { "xxx" });
		try {
			TestSchema.save(user);
		} catch (InvalidStateException i) {
			System.out.println(i.getInvalidValues());
			InvalidValue[] invalidValues = i.getInvalidValues();
			for (InvalidValue invalidValue : invalidValues) {
				System.out.println(invalidValue.getMessage());
			}
			throw i;
		}

	}

}
