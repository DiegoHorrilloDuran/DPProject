package converters;



import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.HandyWorker;

@Component
@Transactional
public class HandyWorkerToStringConverter implements Converter<HandyWorker, String> {

	@Override
	public String convert(final HandyWorker worker) {
		String result;

		if (worker == null)
			result = null;
		else
			result = String.valueOf(worker.getId());

		return result;
	}

}
