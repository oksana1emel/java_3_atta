package com.company;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Player {

    private int playerId;
    private String nickname;

    public List<Progress> progresses;
    public List<Currency> currencies;
    public List<Item> items;

    public List<Progress> getProgresses() {
        return progresses;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Item> getItems() {
        return items;
    }

    public Player(@JsonProperty("playerId") int playerId,
                  @JsonProperty("nickname") String nickname,
                  @JsonProperty("progresses") List<Progress> progresses,
                  @JsonProperty("currencies") List<Currency> currencies,
                  @JsonProperty("items")List<Item> items) {
        this.playerId = playerId;
        this.nickname = nickname;
        this.progresses = new ArrayList<>(progresses);
        this.currencies = new ArrayList<>(currencies);
        this.items = new ArrayList<>(items);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", nickname='" + nickname + '\'' +
                ", progresses=" + progresses +
                ", currencies=" + currencies +
                ", items=" + items +
                '}';
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private Map<Integer, Currency> getMapCurrency() {
        return currencies.stream().collect(Collectors.toMap(Currency::getId, Function.identity()));
    }

    private Map<Integer, Item> getMapItem() {
        return items.stream().collect(Collectors.toMap(Item::getId, Function.identity()));
    }

    private Map<Integer, Progress> getMapProgress() {
        return progresses.stream().collect(Collectors.toMap(Progress::getId, Function.identity()));
    }

    public Currency getCurrencyById(int id) {
        Map<Integer, Currency> currencyMap = getMapCurrency();

        return currencyMap.get(id);
    }

    public Progress getProgressById(int id) {
        Map<Integer, Progress> progressMap = getMapProgress();

        return progressMap.get(id);

    }

    public Item getItemById(int id) {
        Map<Integer, Item> itemMap = getMapItem();

        return itemMap.get(id);
    }

}
