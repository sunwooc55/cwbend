package ch04_pht_01.ems;

import org.springframework.context.support.GenericXmlApplicationContext;

import ch04_pjt_01.ems.member.Student;
import ch04_pjt_01.ems.member.service.EMSInformationService;
import ch04_pjt_01.ems.member.service.PrintStudentInformationService;
import ch04_pjt_01.ems.member.service.StudentDeleteService;
import ch04_pjt_01.ems.member.service.StudentModifyService;
import ch04_pjt_01.ems.member.service.StudentRegisterService;
import ch04_pjt_01.ems.member.service.StudentSelectService;
import ch04_pjt_01.ems.utils.InitSampleData;

public class MainClass {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		InitSampleData initSampleData = ctx.getBean("initSampleData", InitSampleData.class);
		String[] sNums = initSampleData.getsNums();
		String[] sIds = initSampleData.getsIds();
		String[] sPws = initSampleData.getsPws();
		String[] sNames = initSampleData.getsNames();
		int[] sAges = initSampleData.getsAges();
		char[] sGenders = initSampleData.getsGenders();
		String[] sMajors = initSampleData.getsMajors();
		
		StudentRegisterService registerService = ctx.getBean("studentRegisterService", StudentRegisterService.class);
		for(int i=0;i<sNums.length;i++) {
			registerService.register(new Student(sNums[i], sIds[i], sPws[i], sNames[i], sAges[i], sGenders[i], sMajors[i]));
		}
		
		PrintStudentInformationService printStudentInformationService = ctx.getBean("printStudentInformationService", PrintStudentInformationService.class);
		printStudentInformationService.printStudentsInfo();
		
		registerService = ctx.getBean("studentRegisterService", StudentRegisterService.class);
		registerService.register(new Student("hbs006", "deer", "p0006", "melissa", 26, 'w', "Music"));
		
		printStudentInformationService.printStudentsInfo();
		
		StudentSelectService selectService = ctx.getBean("studentSelectService", StudentSelectService.class);
		Student selectStudent = selectService.select("hbs006");
		
		System.out.println("STUDENT START -----------------");
		System.out.print("sNum:" + selectStudent.getsNum() + "\t");
		System.out.print("|sId:" + selectStudent.getsId() + "\t");
		System.out.print("|sPw:" + selectStudent.getsPw() + "\t");
		System.out.print("|sName:" + selectStudent.getsName() + "\t");
		System.out.print("|sAge:" + selectStudent.getsAge() + "\t");
		System.out.print("|sGender:" + selectStudent.getsGender() + "\t");
		System.out.println("|sMajor:" + selectStudent.getsMajor() + "\t");
		System.out.println("END ------------------");
		
		StudentModifyService modifyService = ctx.getBean("studentModifyService", StudentModifyService.class);
		selectStudent.setsId("pig");
		selectStudent.setsAge(27);
		selectStudent.setsMajor("Computer");
		modifyService.modify(selectStudent);
		
		printStudentInformationService.printStudentsInfo();
		
		StudentDeleteService deleteService = ctx.getBean("studentDeleteService", StudentDeleteService.class);
		deleteService.delete("hbs005");
		
		printStudentInformationService.printStudentsInfo();

		EMSInformationService emsInformationService = ctx.getBean("eMSInformationService", EMSInformationService.class);
		emsInformationService.printEMSInformation();
		
		ctx.close();
	}
}
