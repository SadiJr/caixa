package com.omegaeducacional.caixa.service;

import com.omegaeducacional.caixa.domain.cash.CashDesk;
import com.omegaeducacional.caixa.domain.cash.CashDeskDTO;
import com.omegaeducacional.caixa.repository.CashDeskRepository;
import com.omegaeducacional.caixa.utils.CashUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CashDeskService {
    @Autowired
    private CashDeskRepository cashDeskRepository;

    public List<CashDesk> listAll() {
        return cashDeskRepository.findAll();
    }

    public CashDesk createCashDesk(CashDeskDTO cashDeskDTO) {

        CashDesk byDescription = cashDeskRepository.findByDescription(cashDeskDTO.description());

        if (byDescription != null) {
            throw new RuntimeException("Already have one cash desk with this description.");
        }

        Double balance = CashUtils.maskBalance(cashDeskDTO.balance());

        if (balance < 0) {
            throw new RuntimeException("Balance cannot be negative!");
        }

        if (StringUtils.isBlank(cashDeskDTO.description())) {
            throw new RuntimeException("Description cannot be null!");
        }

        CashDesk cashDesk = new CashDesk();
        cashDesk.setDescription(cashDeskDTO.description());
        cashDesk.setBalance(balance);

        return cashDeskRepository.save(cashDesk);
    }

    public CashDesk createCashDesk(String description, Double balance) {
        CashDesk byDescription = cashDeskRepository.findByDescription(description);

        if (byDescription == null) {
            throw new RuntimeException("Cash with this description does not exists.");
        }

        CashDesk cashDesk = new CashDesk();

        if (StringUtils.isNotBlank(description)) {
            cashDesk.setDescription(description);
        }

        if (balance != null) {
            cashDesk.setBalance(CashUtils.maskBalance(balance));
        }

        return cashDeskRepository.save(cashDesk);
    }

    public void delete(Long id) {
        Optional<CashDesk> byId = cashDeskRepository.findById(id);

        byId.ifPresent(cashDesk -> cashDeskRepository.delete(cashDesk));
    }

    public CashDesk findById(Long id) {
        return cashDeskRepository.findById(id).get();
    }

    public CashDesk findByDescription(String description) {
        return cashDeskRepository.findByDescription(description);
    }

    public List<CashDesk> findByBalance(Double balance) {
        return cashDeskRepository.findByBalance(CashUtils.maskBalance(balance));
    }


}
