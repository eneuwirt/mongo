package com.eneuwirt.web.vaadin.application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eneuwirt.web.mongo.DbFactoryBean;
import com.eneuwirt.web.vaadin.screen.MongoDemoScreen;
import com.mongodb.DB;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Window;

@Component(value = "applicationBean")
@Scope("prototype")
public class MyApplication extends com.vaadin.Application implements HttpServletRequestListener
{

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private HttpServletRequest request;

	@Autowired
	DbFactoryBean factory;
	public DB db;


	@Override
	public void init()
	{
		setMainWindow(new Window());

		getMainWindow().addComponent(new MongoDemoScreen(this));
	}


	@Override
	public void onRequestStart(HttpServletRequest request, HttpServletResponse response)
	{
		this.response = response;
		this.request = request;

		try
		{
			this.db = factory.getObject();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void onRequestEnd(HttpServletRequest request, HttpServletResponse response)
	{
		this.response = null;
		this.request = null;

		this.db = null;
	}

}
