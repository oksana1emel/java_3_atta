package com.company.Servlet;

import com.company.Item;
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

public class ItemServlet extends HttpServlet {

    private final Map<Integer, Player> cache = PlayerCache.instanceOrThrow().playerList;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int playerId = Integer.parseInt(req.getParameter("playerId"));
        int id = Integer.parseInt(req.getParameter("id"));
        Player pl = cache.get(playerId);
        if(pl==null || pl.getItemById(id) == null){
            resp.sendError(404);
            return;
        }
        Item item = pl.getItemById(id);
        Writer writer = resp.getWriter();
        writer.write(item.toString());
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = req.getReader();
        Player pl = objectMapper.readValue(reader, Player.class);
        Item item = objectMapper.readValue(reader, Item.class);
        pl.getItems().add(item);
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
        Item itemNew = objectMapper.readValue(reader, Item.class);
        int plId = pl.getPlayerId();
        int id = itemNew.getId();
        Item item = cache.get(plId).getItemById(id);
        if (!cache.containsKey(plId) || item==null) {
            resp.sendError(404);
        }
        pl.getItems().remove(item);
        pl.getItems().add(itemNew);
        cache.put(plId, pl);
        Writer writer = resp.getWriter();
        writer.write(pl.toString());
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = req.getReader();
        Player pl = objectMapper.readValue(reader, Player.class);
        Item item = objectMapper.readValue(reader, Item.class);
        int plId = pl.getPlayerId();
        int id  = item.getId();
        //Item item  = cache.get(plId).getItemById(id);
        if (!cache.containsKey(plId) || cache.get(plId).getItemById(id) == null) {
            resp.sendError(404);
        }
        pl.getItems().remove(item);
        cache.put(plId, pl);
        Writer writer = resp.getWriter();
        writer.write(pl.toString());
        writer.close();
    }
}
