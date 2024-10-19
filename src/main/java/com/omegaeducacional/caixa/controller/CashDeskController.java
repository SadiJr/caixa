package com.omegaeducacional.caixa.controller;

import com.omegaeducacional.caixa.domain.cash.CashDesk;
import com.omegaeducacional.caixa.domain.cash.CashDeskDTO;
import com.omegaeducacional.caixa.service.CashDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cash")
public class CashDeskController {

    @Autowired
    private CashDeskService cashDeskService;

    @GetMapping("/findAll")
    public ResponseEntity<List<CashDesk>> findAllCashDesks() {
        return ResponseEntity.ok(cashDeskService.listAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CashDesk> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cashDeskService.findById(id));
    }

    @GetMapping("/find")
    public ResponseEntity<CashDesk> findByDescription(@RequestParam(name = "description") String description) {
        return ResponseEntity.ok(cashDeskService.findByDescription(description));
    }

//    @GetMapping("/find")
//    public ResponseEntity<List<CashDesk>> findByBalance(@RequestParam(name = "balance") Double balance) {
//        return ResponseEntity.ok(cashDeskService.findByBalance(balance));
//    }

    @PostMapping("/create")
    public ResponseEntity<CashDesk> createCashDesk(@RequestBody CashDeskDTO cashDeskDTO) {
        CashDesk cashDesk = cashDeskService.createCashDesk(cashDeskDTO);
        return ResponseEntity.ok(cashDesk);
    }

    @PutMapping("/update/{description}/{balance}")
    public ResponseEntity<CashDesk> updateCashDesk(@PathVariable String description, @PathVariable Double balance) {
        CashDesk cashDesk = cashDeskService.createCashDesk(description, balance);
        return ResponseEntity.ok(cashDesk);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCashDesk(@PathVariable Long id) {
        cashDeskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
