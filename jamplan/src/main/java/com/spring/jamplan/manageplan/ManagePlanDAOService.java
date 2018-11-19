package com.spring.jamplan.manageplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.MapVO;
import com.spring.jamplan.model.TeamInfoVO;


@Service
public class ManagePlanDAOService {
	@Autowired
	private SqlSession sqlSession;// mybatis(ibatis) 라이브러리가 제공하는 클래스
	@Autowired
	private TeamInfoVO teamVO;
	
	private CalendarMapper calendarMapper;
	private ManagePlanMapper managePlanMapper;
	private ArrayList<TeamInfoVO> planList;
	private ArrayList<CalendarVO> voList;
	
	
	public ArrayList<TeamInfoVO> getOthers(TeamInfoVO teamInfo) {
	      System.out.println("DAO getOthers IN");

	      ArrayList<TeamInfoVO> planList = null;
	      try {
	         managePlanMapper = sqlSession.getMapper(ManagePlanMapper.class);
	         planList = managePlanMapper.getOthers(teamInfo);
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      System.out.println("DAO getOthers Out");
	      return planList;
	   }
	
	//map지은
	 public List<MapVO> getAllPickList(MapVO mapVO) {
	      List<MapVO> allPickList = null;
	      MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
	      System.out.println("getAllPickListDAOS1");
	      System.out.println("mapVO.getSelectDate() = " + mapVO.getSelectDate());
	      System.out.println(mapVO.getSelectDate());
	      allPickList = mapMapper.getAllPickList(mapVO); 
	      System.out.println("getAllPickListDAOS2");
	      return allPickList;
	   }

	   public List<MapVO> getPickList(MapVO mapVO){
	      List<MapVO> pickList = null;
	      MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
	      pickList = mapMapper.getPickList(mapVO);
	      System.out.println("getpicklistDAOS");
	      return pickList;
	   }

	   public int checkPick(MapVO mapVO) {
	      MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
	      int check = mapMapper.checkPick(mapVO);

	      return check;
	   }
	   
	   public void insertMember(MapVO mapVO) {
	      MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
	      System.out.println("insertMemberDAOS1");

	      mapMapper.insertMember(mapVO);   
	      System.out.println("insertMemberDAOS2");

	   }

	   public void deleteMember(MapVO mapVO) {
	      MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
	      System.out.println("deleteMemberDAOS1");
	      
	      System.out.println(mapVO.getPlanNo());
	      mapMapper.deleteMember(mapVO);
	      System.out.println("deleteMemberDAOS2");

	   }

	   public void updatePickCount(MapVO mapVO) {
	      MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
	      System.out.println("updatePickCountDAOS1");

	      mapMapper.updatePickCount(mapVO);
	      System.out.println("updatePickCountDAOS2");

	   }

	   public int pickCount(MapVO mapVO) {
	      MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
	      System.out.println("pickCountDAOS1");

	      int pickNum = mapMapper.pickCount(mapVO);
	      System.out.println("pickCountDAOS2");

	      return pickNum;
	   }

	   public void confirmPlace(Map<String, Object> hm) {
	      MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
	      System.out.println("confirmPlace1");

	      mapMapper.confirmPlace(hm);
	      System.out.println("confirmPlace2");
	   }

	   public void resetPlace(MapVO mapVO) {
	      MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
	      System.out.println("resetPlaceDAOS1");

	      mapMapper.resetPlace(mapVO);
	      System.out.println("resetPlaceDAOS2");
	      
	   }

	   public void changeColor(MapVO mapVO) {
	      MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
	      System.out.println("changeColorDAOS1");
	      mapMapper.changeColor(mapVO);
	      System.out.println("changeColorDAOS2");
	   }

	   public List<MapVO> getSelectDateMap(CalendarVO calendarVO) {      
	      List<MapVO> selectDateList = null;
	      MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
	      System.out.println("날짜받아온거=" + mapMapper.getSelectDateMap(calendarVO));
	      
	      selectDateList = mapMapper.getSelectDateMap(calendarVO);
	      System.out.println("getpicklistDAOS");
	      return selectDateList;
	   }
	   
	   
	   public int deleteSelectDateMap(Map<String, Object> hm) {
		  MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		  System.out.println("deleteSelectDateMap_DAOS1");
	  
		  int check = mapMapper.deleteSelectDateMap(hm);
		  System.out.println("deleteSelectDateMap_DAOS2");
		  
		  return check;
	   }
	   
	//end map 지은

	public ArrayList<TeamInfoVO> getPlans(TeamInfoVO planVO) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);

		planList = new ArrayList<TeamInfoVO>();
		planList = calendarMapper.getAllPlans();
		return planList;
	}

	public ArrayList<TeamInfoVO> getTeamPlan(TeamInfoVO planVO) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);

		planList = new ArrayList<TeamInfoVO>();
		planList = calendarMapper.getTeamPlan(planVO);
		
		return planList;
	}
	
	public TeamInfoVO getPlanRole(HashMap<String, Object> map) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);
		teamVO = calendarMapper.getTeamRole(map);
		return teamVO;
	}
		
	public void getSelectDateFix(CalendarVO vo){
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);
		System.out.println("getSelectFixDate 진입");
		voList = new ArrayList<CalendarVO>();
		try {
			voList = calendarMapper.getSelectFixDate(vo);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		if(voList.size()==0) {
			vo.setDateCount(1);
			vo.setConfirmIndicator(1);
			calendarMapper.insertSelectDate(vo);
		}else {
			if(voList.get(0).getConfirmIndicator()==1) {
				System.out.println("확정 취소");
				vo.setConfirmIndicator(0);
				calendarMapper.updateFixDate(vo);
			}else {
				System.out.println("일정 확정");
				vo.setConfirmIndicator(1);
				calendarMapper.updateFixDate(vo);
			}
		}
	}
	
	public ArrayList<CalendarVO> getMemberId(int planNo){
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);
		voList = new ArrayList<CalendarVO>();
		
		voList = calendarMapper.getMemberId(planNo);
		return voList;
	}
	
	public ArrayList<CalendarVO> getSelectDate(int planNo) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);
		voList = new ArrayList<CalendarVO>();
		try {
			voList = calendarMapper.getCalendarSelectDate(planNo);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	
		// System.out.println(sendList.size());
		// System.out.println(voList.size());
		int k = 0;
		int size = voList.size();
		//일정을 여러명이 선택 했을 때 중복 제거하고 +n 카운트 하나만 보이도록 
		for (int i = 0; i < voList.size(); i++) {
			for (int j = 0; j < voList.size(); j++) {
				if (i != j) {
					if (voList.get(i).getSelectDate().equals(voList.get(j).getSelectDate())) {
						//System.out.println("remove : " + voList.get(j).getSelectDate());
						voList.remove(j);
						k++;
						i = 0;
						break;
					}
				}
			}
			if (i > size - k) {
				break;
			}
		}
		// System.out.println("size : " + voList.size());
		return voList;
	}

	public void insertSelectDate(CalendarVO vo) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);
	
		System.out.println("-------------------------------------");
		System.out.println(vo.getConfirmIndicator());
		System.out.println(vo.getDateCount());
		System.out.println(vo.getId());
		System.out.println(vo.getPlanNo());
		System.out.println(vo.getSelectDate());
		System.out.println("-------------------------------------");
		
		ArrayList<CalendarVO> checkVOList = calendarMapper.checkSelectDate(vo);
		ArrayList<CalendarVO> checkList = calendarMapper.getCountDate(vo);
		int dateCount = 1;
		if (checkVOList.size() == 0) {

			if (checkList.size() != 0) {
				// System.out.println(checkList.size());
				dateCount = checkList.get(0).getDateCount() + 1;
				vo.setDateCount(dateCount);
				calendarMapper.insertSelectDate(vo);
				calendarMapper.updateCountDate(vo);

				// System.out.println("처음 눌렀고, 팀원");
			} else {
				vo.setDateCount(dateCount);
				calendarMapper.insertSelectDate(vo);
				// System.out.println("처음 눌렀고, 혼자");
			}

		} else {
			if (checkList.size() != 0) {
				dateCount = checkList.get(0).getDateCount() - 1;
				vo.setDateCount(dateCount);
				calendarMapper.updateCountDate(vo);
				calendarMapper.deleteSelectDate(vo);
				// System.out.println("두번 눌렀고, 팀원");
			} else {
				calendarMapper.deleteSelectDate(vo);
				// System.out.println("두번 눌렀고, 혼자");
			}

		}

	}
	
}
