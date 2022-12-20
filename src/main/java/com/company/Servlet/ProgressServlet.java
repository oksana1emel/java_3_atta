package com.company.Servlet;

import com.company.Player;
import com.company.PlayerCache;
import com.company.Progress;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

public class ProgressServlet extends HttpServlet {

    private final Map<Integer, Player> cache = PlayerCache.instanceOrThrow().playerList;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int playerId = Integer.parseInt(req.getParameter("playerId"));
        int id = Integer.parseInt(req.getParameter("id"));
        Player pl = cache.get(playerId);
        if(pl==null || pl.getProgressById(id) == null){
            resp.sendError(404);
            return;
        }
        Progress pr = pl.getProgressById(id);
        Writer writer = resp.getWriter();
        writer.write(pr.toString());
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = req.getReader();
        Player pl = objectMapper.readValue(reader, Player.class);
        Progress pr = objectMapper.readValue(reader, Progress.class);
        pl.getProgresses().add(pr);
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
        Progress prNew = objectMapper.readValue(reader, Progress.class);
        int plId = pl.getPlayerId();
        int id = prNew.getId();
        Progress pr = cache.get(plId).getProgressById(id);
        if (!cache.containsKey(plId) || pr==null) {
            resp.sendError(404);
        }
        pl.getProgresses().remove(pr);
        pl.getProgresses().add(prNew);
        cache.put(plId, pl);
        Writer writer = resp.getWriter();
        writer.write(pl.toString());
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = req.getReader();
        Player pl = objectMapper.readValue(reader, Player.class);
        Progress pr = objectMapper.readValue(reader, Progress.class);
        int plId = pl.getPlayerId();
        int id  = pr.getId();
        if (!cache.containsKey(plId) || cache.get(plId).getProgressById(id) == null) {
            resp.sendError(404);
        }
        pl.getProgresses().remove(pr);
        cache.put(plId, pl);
        Writer writer = resp.getWriter();
        writer.write(pl.toString());
        writer.close();
    }
}
