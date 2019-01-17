package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.CreditCard;

@Component
@Transactional
public class CreditCardToStringConverter implements
		Converter<CreditCard, String> {

	@Override
	public String convert(CreditCard creditCard) {

		String result;

		if (creditCard == null) {
			result = null;
		} else {
			result = String.valueOf(creditCard.getId());
		}

		return result;
	}

}
