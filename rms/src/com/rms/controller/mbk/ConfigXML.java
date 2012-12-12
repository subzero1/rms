package com.rms.controller.mbk;

import java.util.List;

/**
 * @description:
 * 
 * @class name:com.rms.controller.mbk.ConfigXML
 * @author net Dec 12, 2012
 */
public interface ConfigXML {
	public String getConfigFilePath(String config,String webinfpath) throws Exception;
	public String getConfigFilePath(String config,String webinfpath,String importXML) throws Exception;
	public List getTagList(String configFilePath,String webinfpath) throws Exception;
	public List getTagList(String configFilePath,String webinfpath,String tagName) throws Exception;
	public List getTagListByConfig(String config,String webinfpath,String tagName) throws Exception; }
