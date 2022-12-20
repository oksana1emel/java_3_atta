package com.company.Servlet;

import com.company.Currency;
import com.company.Player;
import com.company.PlayerCache;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

public class CurrencyServlet extends HttpServlet {

    private final Map<Integer, Player> cache = PlayerCache.instanceOrThrow().playerList;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int playerId = Integer.parseInt(req.getParameter("playerId"));
        int id = Integer.parseInt(req.getParameter("id"));
        Player pl = cache.get(playerId);
        if(pl==null || pl.getCurrencyById(id) == null){
            resp.sendError(404);
            return;
        }
        Currency cur = pl.getCurrencyById(id);
        Writer writer = resp.getWriter();
        writer.write(cur.toString());
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = req.getReader();
        Player pl = objectMapper.readValue(reader, Player.class);
        Currency cur = objectMapper.readValue(reader, Currency.class);
        pl.getCurrencies().add(cur);
        int plId = pl.getPlayerId();
        cache.put(plId, pl);
        Writer writer = resp.getWriter();
        writer.write(pl.toString());
        writer.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = req.getReader();
        Player pl = objectMapper.readValue(reader, Player.class);
        Currency curNew = objectMapper.readValue(reader, Currency.class);
        int plId = pl.getPlayerId();
        int id = curNew.getId();
        Currency cur = cache.get(plId).getCurrencyById(id);
        if (!cache.containsKey(plId) || cur==null) {
            resp.sendError(404);
        }
        pl.getCurrencies().remove(cur);
        pl.getCurrencies().add(curNew);
        cache.put(plId, pl);
        Writer writer = resp.getWriter();
        writer.write(pl.toString());
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = req.getReader();
        Player pl = objectMapper.readValue(reader, Player.class);
        Currency cur = objectMapper.readValue(reader, Currency.class);
        int plId = pl.getPlayerId();
        int id  = cur.getId();
        //Item item  = cache.get(plId).getItemById(id);
        if (!cache.containsKey(plId) || cache.get(plId).getCurrencyById(id) == null) {
            resp.sendError(404);
        }
        pl.getCurrencies().remove(cur);
        cache.put(plId, pl);
        Writer writer = resp.getWriter();
        writer.write(pl.toString());
        writer.close();
    }
}
