
package converters;

import java.net.URLEncoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Money;

@Component
@Transactional
public class MoneyToStringConverter implements Converter<Money, String> {

	@Override
	public String convert(Money money) {

		String result;
		StringBuilder builder;

		if (money == null) {
			result = null;
		} else {
			try {
				builder = new StringBuilder();
				builder.append(URLEncoder.encode(money.getCurrency(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(Double.toString(money.getAmount()), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(Integer.toString(money.getVatTax()), "UTF-8"));
				result = builder.toString();
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}
		}
		return result;
	}
}
