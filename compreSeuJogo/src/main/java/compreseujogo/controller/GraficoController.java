package compreseujogo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.optionconfig.elements.Elements;
import org.primefaces.model.charts.optionconfig.elements.ElementsLine;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.radar.RadarChartDataSet;
import org.primefaces.model.charts.radar.RadarChartModel;
import org.primefaces.model.charts.radar.RadarChartOptions;

import compreseujogo.facade.Facade;
import compreseujogo.model.entity.Venda;

@RequestScoped
@ManagedBean(name = "graficoBean")
public class GraficoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private PieChartModel pieModel;
	private RadarChartModel radarModel;
	
	@PostConstruct
    public void init() {
        createPieModel();
        createRadarModel();
    }

	private void createPieModel() {
		pieModel = new PieChartModel();
		ChartData data = new ChartData();
		Facade facade = new Facade();
		List<Venda> venda = new Facade().listaVenda("", null);
		PieChartDataSet dataSet = new PieChartDataSet();
		List<Number> values = new ArrayList<>();
		values.add(venda.stream().filter(Venda -> Venda.getVendedor() == null).count());
		values.add(venda.stream().filter(Venda -> Venda.getVendedor() != null).count());
		//values.add(100);
		dataSet.setData(values);

		List<String> bgColors = new ArrayList<>();
		bgColors.add("rgb(232, 30, 30)");
		bgColors.add("rgb(54, 162, 235)");
		//bgColors.add("rgb(255, 205, 86)");
		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);
		List<String> labels = new ArrayList<>();
		labels.add("Online");
		labels.add("Fisica");
		//labels.add("Yellow");
		data.setLabels(labels);

		pieModel.setData(data);
	}
	public void createRadarModel() {
        radarModel = new RadarChartModel();
        ChartData data = new ChartData();
         
        RadarChartDataSet radarDataSet = new RadarChartDataSet();
        radarDataSet.setLabel("My First Dataset");
        radarDataSet.setFill(true);
        radarDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
        radarDataSet.setBorderColor("rgb(255, 99, 132)");
        radarDataSet.setPointBackgroundColor("rgb(255, 99, 132)");
        radarDataSet.setPointBorderColor("#fff");
        radarDataSet.setPointHoverBackgroundColor("#fff");
        radarDataSet.setPointHoverBorderColor("rgb(255, 99, 132)");
        List<Number> dataVal = new ArrayList<>();
        dataVal.add(65);
        dataVal.add(59);
        dataVal.add(90);
        dataVal.add(81);
        dataVal.add(56);
        dataVal.add(55);
        dataVal.add(40);
        radarDataSet.setData(dataVal);
         
        RadarChartDataSet radarDataSet2 = new RadarChartDataSet();
        radarDataSet2.setLabel("My Second Dataset");
        radarDataSet2.setFill(true);
        radarDataSet2.setBackgroundColor("rgba(54, 162, 235, 0.2)");
        radarDataSet2.setBorderColor("rgb(54, 162, 235)");
        radarDataSet2.setPointBackgroundColor("rgb(54, 162, 235)");
        radarDataSet2.setPointBorderColor("#fff");
        radarDataSet2.setPointHoverBackgroundColor("#fff");
        radarDataSet2.setPointHoverBorderColor("rgb(54, 162, 235)");
        List<Number> dataVal2 = new ArrayList<>();
        dataVal2.add(28);
        dataVal2.add(48);
        dataVal2.add(40);
        dataVal2.add(19);
        dataVal2.add(96);
        dataVal2.add(27);
        dataVal2.add(100);
        radarDataSet2.setData(dataVal2);
         
        data.addChartDataSet(radarDataSet);
        data.addChartDataSet(radarDataSet2);
         
        List<String> labels = new ArrayList<>();
        labels.add("Eating");
        labels.add("Drinking");
        labels.add("Sleeping");
        labels.add("Designing");
        labels.add("Coding");
        labels.add("Cycling");
        labels.add("Running");
        data.setLabels(labels);
         
        /* Options */
        RadarChartOptions options = new RadarChartOptions();
        Elements elements = new Elements();
        ElementsLine elementsLine = new ElementsLine();
        elementsLine.setTension(0);
        elementsLine.setBorderWidth(3);
        elements.setLine(elementsLine);
        options.setElements(elements);
         
        radarModel.setOptions(options);
        radarModel.setData(data);
    }
	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public RadarChartModel getRadarModel() {
		return radarModel;
	}

	public void setRadarModel(RadarChartModel radarModel) {
		this.radarModel = radarModel;
	}

}
