/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.modules.weixin.exception.WeiXinException;
import com.thinkgem.jeesite.modules.weixin.http.WeixinHttpCore;
import com.thinkgem.jeesite.modules.weixin.vo.AccessTokenRep;
import com.thinkgem.jeesite.modules.weixin.vo.Custom;
import com.thinkgem.jeesite.modules.weixin.vo.CustomListRep;
import com.thinkgem.jeesite.modules.weixin.vo.CustomOnlineListRep;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinCustom;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinCustomDao;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 微信客服信息表Service
 * @author mawj
 * @version 2016-10-31
 */
@Service
@Transactional(readOnly = true)
public class WeixinCustomService extends CrudService<WeixinCustomDao, WeixinCustom> {

	@Value("#{APP_PROP['config.weixin.custom']}")
	String weixinCustomHost;

	@Autowired
	WeixinHttpCore weixinHttpCore;

	public WeixinCustom get(String id) {
		return super.get(id);
	}
	
	public List<WeixinCustom> findList(WeixinCustom weixinCustom) {
		return super.findList(weixinCustom);
	}
	
	public Page<WeixinCustom> findPage(Page<WeixinCustom> page, WeixinCustom weixinCustom) {
		return super.findPage(page, weixinCustom);
	}
	
	@Transactional(readOnly = false)
	public Page<WeixinCustom> getList(WeixinCustom weixinCustom,String accessToken) throws WeiXinException {
		Page<WeixinCustom> page = new Page<WeixinCustom>();
		List<WeixinCustom> customsList = new ArrayList<>(0);
		page.setList(customsList);
		page.setPageNo(1);
		page.setPageSize(30);

		String repList = weixinHttpCore.getkfList(accessToken);
		String repOnlineList = weixinHttpCore.getOnlinekfList(accessToken);
		CustomOnlineListRep customOnlineListRep = null;
		if(repOnlineList.contains("kf_online_list")){
			customOnlineListRep = JsonMapper.getInstance().fromJson(repOnlineList,CustomOnlineListRep.class);
		}

		if(1==1)repList = "{\"kf_list\": [{\"kf_account\": \"test1@test\",\"kf_headimgurl\": \"http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0\",\"kf_id\": \"1001\",\"kf_nick\": \"ntest1\"},{\"kf_account\": \"test2@test\",\"kf_headimgurl\": \"http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0\",\"kf_id\": \"1002\",\"kf_nick\": \"ntest2\"},{\"kf_account\": \"test3@test\",\"kf_headimgurl\": \"http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0\",\"kf_id\": \"1003\",\"kf_nick\": \"ntest3\"}]}";
		if(repList.contains("{\"kf_list")){
			CustomListRep customListRep = JsonMapper.getInstance().fromJson(repList,CustomListRep.class);

			for(Custom custom:customListRep.getKfList()){
				WeixinCustom entity = new WeixinCustom();
				List<WeixinCustom> entitysInDB = dao.findListByKFAccount(custom.getKfAccount());
				if(entitysInDB!=null&&entitysInDB.size()>0){
					WeixinCustom entityInDB = entitysInDB.get(0);
					entity = entityInDB;
				}

				entity.setKfAccount(custom.getKfAccount());
				entity.setKfHeadimgurl(custom.getKfHeadimgurl());
				entity.setKfId(custom.getKfId());
				entity.setKfNick(custom.getKfNick());
				if(customOnlineListRep!=null){
					for (Custom customOnline:customOnlineListRep.getKfList()){
						if(customOnline.getKfAccount().equals(entity.getKfAccount())){
							entity.setStatus(customOnline.getStatus());
						}
					}
				}
				save(entity);
				customsList.add(entity);
			}
			page.setCount(customsList.size());
			return page;
		}else
			throw new WeiXinException("客服列表获取失败");

	}

	@Transactional(readOnly = false)
	public void save(WeixinCustom weixinCustom,String acccessToken) throws WeiXinException {
		boolean isSave = true;
		String url = weixinCustomHost+"/kfaccount/add?access_token="+acccessToken;
		if(weixinCustom.getId()!=null){
			url = weixinCustomHost+"/kfaccount/update?access_token="+acccessToken;
			isSave = false;
		}

		RestTemplate restTemplate = new RestTemplate();
		logger.debug("ready add custome account from url "+url);
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		String data = "{\"kf_account\" : \""+weixinCustom.getKfAccount()+"\",\"nickname\" : \""+weixinCustom.getKfNick()+"\",\"password\" : \""+Md5Crypt.md5Crypt(weixinCustom.getPassword().getBytes())+"\"}";
		HttpEntity<String> formEntity = new HttpEntity<String>(data, headers);
		String rep = restTemplate.postForObject(url, formEntity, String.class);

		String localPath = weixinCustom.getKfHeadimgurl().substring(weixinCustom.getKfHeadimgurl().indexOf("userfiles"));
		File headFile = new File(Global.getUserfilesBaseDir()+localPath);


//		http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT


		if(rep.contains("\"errcode\" : 0")){
			//上传头像
			String headUrl = weixinCustomHost+"/kfaccount/uploadheadimg?access_token="+acccessToken+"&kf_account="+weixinCustom.getKfAccount();
			RestTemplate rest = new RestTemplate();
			FileSystemResource resource = new FileSystemResource(headFile);
			MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
			param.add("file", resource);
			String uploadRep = rest.postForObject(url, param, String.class);
			logger.debug("uploadRep = "+uploadRep);
			super.save(weixinCustom);
		}else {
			if(isSave)
				throw new WeiXinException("客服填加失败");
			else
				throw new WeiXinException("客服修改失败");
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinCustom weixinCustom,String acccessToken) throws WeiXinException {
		String url = weixinCustomHost+"/kfaccount/del?access_token="+acccessToken;

		RestTemplate restTemplate = new RestTemplate();
		logger.debug("ready add custome account from url "+url);
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		String data = "{\"kf_account\" : \""+weixinCustom.getKfAccount()+"\",\"nickname\" : \""+weixinCustom.getKfNick()+"\",\"password\" : \""+Md5Crypt.md5Crypt(weixinCustom.getPassword().getBytes())+"\"}";
		HttpEntity<String> formEntity = new HttpEntity<String>(data, headers);
		String rep = restTemplate.postForObject(url, formEntity, String.class);

		if(rep.contains("\"errcode\" : 0")){
			super.save(weixinCustom);
		}else {
			throw new WeiXinException("客服删除失败");
		}

		super.delete(weixinCustom);
	}

	/**
	 * 创建会话
	 * @param kfAccount
	 * @param openId
     */
	public void createSession(String kfAccount,String openId){

	}

}