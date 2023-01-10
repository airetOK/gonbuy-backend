package com.games.gonbuy.service.impl;

import com.games.gonbuy.service.RollDiceService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RollDiceServiceImpl implements RollDiceService {

    @Override
    public Integer rollDice() {
        Random random =  new Random();
        return random.ints(1, 7)
                .findFirst()
                .getAsInt();
    }
}
