<%@page import="wordstudy.domain.Board"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%response.setHeader("Access-Control-Allow-Origin", "*"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
ArrayList<Board> list = new ArrayList<>();
list.add(new Board(1, "제목 11111", "홍길동", 10));
list.add(new Board(2, "제목 222222", "유관순", 100));

%>
<%=new Gson().toJson(list)%>