
package converters;

import java.net.URLDecoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Money;

@Component
@Transactional
public class StringToMoneyConverter implements Converter<String, Money> {

	@Override
	public Money convert(final String text) {
		Money result;
		String parts[];

		if (text == null) {
			result = null;
		} else {
			try {
				parts = text.split("\\|");
				result = new Money();
				result.setCurrency(URLDecoder.decode(parts[0], "UTF-8"));
				result.setAmount(Double.valueOf(URLDecoder.decode(parts[1], "UTF-8")));
				result.setVatTax(Integer.valueOf(URLDecoder.decode(parts[2], "UTF-8")));

			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}
		}
		return result;

	}
}
