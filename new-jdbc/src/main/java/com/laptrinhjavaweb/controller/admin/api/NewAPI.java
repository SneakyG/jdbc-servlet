package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-new" })
public class NewAPI extends HttpServlet {

	@Inject
	private INewService newService;

	private static final long serialVersionUID = -7517182978030195404L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		// Nhâòn data(json) týÌ Client vaÌ convert thaÌnh String
		NewModel newModel = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		// bind String json vaÌo model võìi caìc field týõng ýìng vaÌ gaìn vaÌo model
		newModel = newService.save(newModel);
		// Convert model(kêìt quaÒ maÌ Client request) thaÌnh json ðêÒ response(traÒ
		// kêìt quaÒ request) laòi cho Client
		mapper.writeValue(resp.getOutputStream(), newModel);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewModel updateNew = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		updateNew.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		updateNew = newService.update(updateNew);
		mapper.writeValue(resp.getOutputStream(), updateNew);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
//		NewModel deleteNew = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		Long[] ids = HttpUtil.of(req.getReader()).toLongArray();
//		newService.delete(deleteNew.getIds());
		newService.delete(ids);
		mapper.writeValue(resp.getOutputStream(), "{}");
	}
}
