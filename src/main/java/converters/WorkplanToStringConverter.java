
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Workplan;

@Component
@Transactional
public class WorkplanToStringConverter implements Converter<Workplan, String> {

	@Override
	public String convert(Workplan workplan) {

		String result;

		if (workplan == null) {
			result = null;
		} else {
			result = String.valueOf(workplan.getId());
		}
		return result;
	}
}
