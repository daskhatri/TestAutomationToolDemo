package com.finexus.automation.canvas;

import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
//import com.canvasjs.chart.daos.CanvasjsChartDao;
@Service
public class CanvasjsChartServiceImpl implements CanvasjsChartService {
 
	@Autowired
	private CanvasjsChartDao canvasjsChartDao;
 
	public void setCanvasjsChartDao(CanvasjsChartDao canvasjsChartDao) {
		this.canvasjsChartDao = canvasjsChartDao;
	}
 
	@Override
	public List<Map<Object, Object>> getCanvasjsChartData(Long id) {
		return canvasjsChartDao.getCanvasjsChartData(id);
	}

	@Override
	public Long getTopRecordId() {
		Long id = canvasjsChartDao.getTopRecordId();
		return id;
	}
 
}