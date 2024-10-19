package com.omegaeducacional.caixa.service;

import com.omegaeducacional.caixa.domain.cash.CashDesk;
import com.omegaeducacional.caixa.domain.enums.TypeMovimentation;
import com.omegaeducacional.caixa.domain.movimentation.Movimentation;
import com.omegaeducacional.caixa.domain.movimentation.MovimentationDTO;
import com.omegaeducacional.caixa.repository.CashDeskRepository;
import com.omegaeducacional.caixa.repository.MovimentationRepository;
import com.omegaeducacional.caixa.utils.CashUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class MovimentationService {

    @Autowired
    private MovimentationRepository movimentationRepository;

    @Autowired
    private CashDeskRepository cashDeskRepository;

    @Autowired
    private CashDeskService cashDeskService;

    public List<Movimentation> findAll() {
        return movimentationRepository.findAll();
    }

    public Movimentation findById(Long id) {
        return movimentationRepository.findById(id).get();
    }

    public Movimentation createMovimentation(MovimentationDTO data) throws ParseException {
        CashDesk cashDesk = cashDeskService.findById(data.deskCash());
        Double value = CashUtils.maskBalance(data.value());

        if (value < 0) {
            throw new RuntimeException("Value cannot be negative!");
        }

        if (isAnyParametersNull(data)) {
            throw new RuntimeException("All parameters need to be passed!");
        }

        Movimentation movimentation = new Movimentation();
        movimentation.setTypeMovimentation(data.typeMovimentation());

        movimentation.setDate(maskDate(data.date()));
        movimentation.setValue(data.value());
        movimentation.setDescription(data.description());
        movimentation.setCashDesk(cashDesk);

        Movimentation save = movimentationRepository.save(movimentation);

        Double newBalance = calcNewCashDeskBalance(data.typeMovimentation(), cashDesk, value);
        cashDesk.setBalance(newBalance);
        cashDeskRepository.save(cashDesk);

        return save;
    }

    private Double calcNewCashDeskBalance(TypeMovimentation typeMovimentation, CashDesk cashDesk, Double value) {
        Double newBalance = null;
        if (TypeMovimentation.E.equals(typeMovimentation)) {
            newBalance = CashUtils.maskBalance(cashDesk.getBalance() + value);
        } else {
            newBalance = CashUtils.maskBalance(cashDesk.getBalance() - value);
        }
        return newBalance;
    }

    public List<Movimentation> filterByDateAndCashDesk(int year, int month, Long cashDeskId) {
        String monthQuery = String.valueOf(month);
        if (month < 10) {
            monthQuery = "0" + String.valueOf(month);
        }
        String filter = String.format("%s-%s", year, monthQuery);
        return movimentationRepository.findByYearAndMonthAndCashDeskId(filter, cashDeskId);
    }


    public List<Movimentation> findByCashId(Long id) {
        CashDesk cashDesk = cashDeskService.findById(id);

        if (cashDesk == null) {
            return List.of();
        }

        return movimentationRepository.findByCashDeskId(id);
    }

    private Date maskDate(Long inputDate){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        String formattedDate = formatter.format(inputDate);
        try {
            return formatter.parse(formattedDate);
        } catch (ParseException e) {
            throw new RuntimeException(String.format("Cannot parse date [%s]. The supported formats are [dd/MM/yyyy].", inputDate));
        }
    }

    private boolean isAnyParametersNull(MovimentationDTO data) {
        Long date = data.date();
        Double value = data.value();
        Long cashDeskId = data.deskCash();
        String description = data.description();
        TypeMovimentation typeMovimentation = data.typeMovimentation();

        return ObjectUtils.anyNull(date, value, cashDeskId, description, typeMovimentation);
    }

    public void deleteMovimentation(Long id) {
        Optional<Movimentation> movimentationOptional = movimentationRepository.findById(id);

        if (movimentationOptional.isPresent()) {
            Movimentation movimentation = movimentationOptional.get();

            CashDesk cashDesk = movimentation.getCashDesk();
            Double newBalance = calcNewCashDeskBalance(movimentation.getTypeMovimentation(), cashDesk, movimentation.getValue());
            cashDesk.setBalance(newBalance);
            cashDeskRepository.save(cashDesk);

            movimentationRepository.delete(movimentation);
        }
    }
}
