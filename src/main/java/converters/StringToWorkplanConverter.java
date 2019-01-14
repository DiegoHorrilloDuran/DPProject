
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.WorkplanRepository;
import domain.Workplan;

@Component
@Transactional
public class StringToWorkplanConverter implements Converter<String, Workplan> {

	@Autowired
	WorkplanRepository	workplanRepository;


	@Override
	public Workplan convert(String text) {
		Workplan result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = this.workplanRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;

	}

}
