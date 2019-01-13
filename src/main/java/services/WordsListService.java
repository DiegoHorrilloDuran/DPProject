
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WordsListRepository;
import domain.Admin;
import domain.WordsList;

@Service
@Transactional
public class WordsListService {

	@Autowired
	private WordsListRepository	wordsListRepository;
	@Autowired
	private AdminService		adminService;


	public WordsListService() {
		super();
	}

	//CREATE
	public WordsList create() {
		Collection<String> spamWordList = new ArrayList<String>();
		Collection<String> positiveWords = new ArrayList<String>();
		Collection<String> negativeWords = new ArrayList<String>();
		WordsList res = new WordsList();
		res.setSpamWordList(spamWordList);
		res.setPositiveWords(positiveWords);
		res.setNegativeWords(negativeWords);
		return res;
	}

	//SAVE
	public WordsList save(WordsList wordsList) {
		Assert.notNull(wordsList);
		return this.wordsListRepository.saveAndFlush(wordsList);
	}

	//DELETE
	public void delete(WordsList wordsList) {
		Assert.notNull(wordsList);
		Collection<Admin> adm = this.adminService.findAll();
		for (Admin a : adm)
			if (a.getWordsLists().contains(wordsList)) {
				a.getWordsLists().remove(wordsList);
				this.adminService.save(a);
			}
		this.wordsListRepository.delete(wordsList);
	}

	//FINDONE
	public WordsList findOne(int id) {
		WordsList res;
		res = this.wordsListRepository.findOne(id);
		//Assert.notNull(res);
		return res;
	}

	//FINDALL
	public Collection<WordsList> findAll() {
		Collection<WordsList> res;
		res = this.wordsListRepository.findAll();
		Assert.notNull(res);
		return res;
	}

}
