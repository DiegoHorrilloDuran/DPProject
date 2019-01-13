package converters;

import java.net.URLEncoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Admin;

@Component
@Transactional
public class AdminToStringConverter implements Converter<Admin, String> {

	@Override
	public String convert(final Admin admin) {
		String result;
		StringBuilder builder;

		if (admin == null) {
			result = null;
		} else {
			try {
				builder = new StringBuilder();
				builder.append(URLEncoder.encode(admin.getName(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(admin.getMiddleName(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(admin.getSurname(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(admin.getPhoto(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(admin.getEmail(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(admin.getPhoneNumber(),
						"UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(admin.getAddress(), "UTF-8"));
				result = builder.toString();
			} catch (Throwable oops) {
				throw new RuntimeException(oops);
			}
		}

		return result;
	}

}
