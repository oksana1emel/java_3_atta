package com.company.Servlet;

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

public class PlayerServlet extends HttpServlet {

    private final Map<Integer, Player> cache = PlayerCache.instanceOrThrow().playerList;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int playerId = Integer.parseInt(req.getParameter("playerId"));
        Player pl = cache.get(playerId);
        if (pl == null) {
            resp.sendError(404);
            return;
        }
        Writer writer = resp.getWriter();
        writer.write(pl.toString());
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = req.getReader();
        Player pl = objectMapper.readValue(reader, Player.class);
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
        int plId = pl.getPlayerId();
        if (!cache.containsKey(plId)) {
            resp.sendError(404);
        }
        cache.put(plId, pl);
        Writer writer = resp.getWriter();
        writer.write(pl.toString());
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = req.getReader();
        Player pl = objectMapper.readValue(reader, Player.class);
        int plId = pl.getPlayerId();
        if (!cache.containsKey(plId)) {
            resp.sendError(404);
        }
        cache.remove(plId);
        Writer writer = resp.getWriter();
        writer.write(pl.toString());
        writer.close();
    }
}
