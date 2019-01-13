
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FixUpTaskFinderRepository;
import domain.FixUpTaskFinder;
import domain.HandyWorker;
import domain.Money;

@Service
@Transactional
public class FixUpTaskFinderService {

	//Managed Repository-------------------------------------------------------

	@Autowired
	private FixUpTaskFinderRepository	fixUpTaskFinderRepository;

	@Autowired
	private HandyWorkerService			handyWorkerService;


	// Supporting services ----------------------------------------------------	

	public FixUpTaskFinderService() {
		super();
	}

	//Simple CRUD methods-----------------------------------------------------

	public FixUpTaskFinder create() {
		FixUpTaskFinder res = new FixUpTaskFinder();
		return res;

	}

	public Collection<FixUpTaskFinder> findAll() {
		Collection<FixUpTaskFinder> res;
		res = this.fixUpTaskFinderRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public FixUpTaskFinder findOne(int id) {
		Assert.isTrue(id != 0);
		FixUpTaskFinder res;
		res = this.fixUpTaskFinderRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public FixUpTaskFinder save(FixUpTaskFinder f) {
		Assert.notNull(f);
		return this.fixUpTaskFinderRepository.save(f);
	}

	public FixUpTaskFinder edit(String tickerKeyWord, String description, String address, String category, String warranty, Money minPrice, Money maxPrice, Date minDate, Date maxDate) {

		FixUpTaskFinder f = new FixUpTaskFinder();
		f.setTickerKeyWord(tickerKeyWord);
		f.setDescription(description);
		f.setAddress(address);
		f.setCategory(category);
		f.setWarranty(warranty);
		f.setMinPrice(minPrice);
		f.setMaxPrice(maxPrice);
		f.setMinDate(minDate);
		f.setMaxDate(maxDate);

		return f;
	}

	public void delete(FixUpTaskFinder f) {
		Assert.notNull(f);
		Collection<HandyWorker> han = this.handyWorkerService.findAll();
		for (HandyWorker h : han)
			if (h.getFixUpTaskFinders().contains(f)) {
				h.getFixUpTaskFinders().remove(f);
				this.handyWorkerService.save(h);
			}
		this.fixUpTaskFinderRepository.delete(f);
	}
}
