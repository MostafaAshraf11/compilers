package csen1002.tests.task5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task5.CfgLeftRecElim;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task5TestsBatch5 {

	@Test
	public void testCfgLeftRecursionElimination1() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;Z;P;F;H;R#k;l;o;u;x#S/PuZ,HPuS;Z/xHZ,lPuPo;P/ZHRo,u;F/u,ZP,HkP,FFPHP,FoHPP;H/PlRoS,uF,PPS,HoH,HHkF;R/RSu,PFR,PR,xHoP");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;Z;P;F;H;R;F';H';R'#k;l;o;u;x#S/PuZ,HPuS;Z/xHZ,lPuPo;P/xHZHRo,lPuPoHRo,u;F/uF',xHZPF',lPuPoPF',HkPF';H/xHZHRolRoSH',lPuPoHRolRoSH',ulRoSH',uFH',xHZHRoPSH',lPuPoHRoPSH',uPSH';R/xHZHRoFRR',lPuPoHRoFRR',uFRR',xHZHRoRR',lPuPoHRoRR',uRR',xHoPR';F'/FPHPF',oHPPF',e;H'/oHH',HkFH',e;R'/SuR',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination2() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;G;K;Q;E#f;h;p#S/GQ,hGQ,EpEEQ;G/Gf,pKQK,pK,EEKQ,Gh;K/hEhG,hSQ;Q/GhS,SSKK,EfE;E/EpS,GhE,EES,hSp,GKQhQ,fKhG");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;G;K;Q;E;G';E'#f;h;p#S/GQ,hGQ,EpEEQ;G/pKQKG',pKG',EEKQG';K/hEhG,hSQ;Q/pKQKG'hS,pKG'hS,EEKQG'hS,pKQKG'QSKK,pKG'QSKK,EEKQG'QSKK,hGQSKK,EpEEQSKK,EfE;E/pKQKG'hEE',pKG'hEE',hSpE',pKQKG'KQhQE',pKG'KQhQE',fKhGE';G'/fG',hG',e;E'/pSE',EKQG'hEE',ESE',EKQG'KQhQE',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination3() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;N;M;B;O#g;p;z#S/BSp,MzS;N/NSBzS,MgMz,zNO,BMSpB,zBOO,NS;M/SSM,zSMB,NpMOg;B/zBSz,zN;O/NSzNz,OMN,MNzS,ONNz");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;N;M;B;O;N';M';O'#g;p;z#S/BSp,MzS;N/MgMzN',zNON',BMSpBN',zBOON';M/BSpSMM',zSMBM',zNON'pMOgM',BMSpBN'pMOgM',zBOON'pMOgM';B/zBSz,zN;O/zBSzSpSMM'gMzN'SzNzO',zNSpSMM'gMzN'SzNzO',zSMBM'gMzN'SzNzO',zNON'pMOgM'gMzN'SzNzO',zBSzMSpBN'pMOgM'gMzN'SzNzO',zNMSpBN'pMOgM'gMzN'SzNzO',zBOON'pMOgM'gMzN'SzNzO',zNON'SzNzO',zBSzMSpBN'SzNzO',zNMSpBN'SzNzO',zBOON'SzNzO',zBSzSpSMM'NzSO',zNSpSMM'NzSO',zSMBM'NzSO',zNON'pMOgM'NzSO',zBSzMSpBN'pMOgM'NzSO',zNMSpBN'pMOgM'NzSO',zBOON'pMOgM'NzSO';N'/SBzSN',SN',e;M'/zSSMM',gMzN'pMOgM',e;O'/MNO',NNzO',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination4() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;G;P;E;Y#j;v;w#S/vEv,wEjP,YjG;G/EESP,vSYPj,GGYGj,w,SES;P/GG,GEjEj,PPEwG,PGG,jP,v;E/wYwSw,EGjGS,vSvPY,jG,j;Y/jYw,PYGwG,YGwY,YE,GwPw,j");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;G;P;E;Y;G';P';E';Y'#j;v;w#S/vEv,wEjP,YjG;G/EESPG',vSYPjG',wG',vEvESG',wEjPESG',YjGESG';P/EESPG'GP',vSYPjG'GP',wG'GP',vEvESG'GP',wEjPESG'GP',YjGESG'GP',EESPG'EjEjP',vSYPjG'EjEjP',wG'EjEjP',vEvESG'EjEjP',wEjPESG'EjEjP',YjGESG'EjEjP',jPP',vP';E/wYwSwE',vSvPYE',jGE',jE';Y/jYwY',wYwSwE'ESPG'GP'YGwGY',vSvPYE'ESPG'GP'YGwGY',jGE'ESPG'GP'YGwGY',jE'ESPG'GP'YGwGY',vSYPjG'GP'YGwGY',wG'GP'YGwGY',vEvESG'GP'YGwGY',wEjPESG'GP'YGwGY',wYwSwE'ESPG'EjEjP'YGwGY',vSvPYE'ESPG'EjEjP'YGwGY',jGE'ESPG'EjEjP'YGwGY',jE'ESPG'EjEjP'YGwGY',vSYPjG'EjEjP'YGwGY',wG'EjEjP'YGwGY',vEvESG'EjEjP'YGwGY',wEjPESG'EjEjP'YGwGY',jPP'YGwGY',vP'YGwGY',wYwSwE'ESPG'wPwY',vSvPYE'ESPG'wPwY',jGE'ESPG'wPwY',jE'ESPG'wPwY',vSYPjG'wPwY',wG'wPwY',vEvESG'wPwY',wEjPESG'wPwY',jY';G'/GYGjG',e;P'/PEwGP',GGP',e;E'/GjGSE',e;Y'/jGESG'GP'YGwGY',jGESG'EjEjP'YGwGY',GwYY',EY',jGESG'wPwY',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination5() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;V;C;I;U#c;r;s;x#S/USIx,UCU,SI,ScS,IUS,cCUrI;V/USsC,ScVcS,IsSC,Vr,SUsS;C/US,sUxUV,Us,s,UxUsU;I/Sx,IVrVU,IxU,VsSx;U/sCIUx,sC");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;V;C;I;U;S';V';I'#c;r;s;x#S/USIxS',UCUS',IUSS',cCUrIS';V/USsCV',USIxS'cVcSV',UCUS'cVcSV',IUSS'cVcSV',cCUrIS'cVcSV',IsSCV',USIxS'UsSV',UCUS'UsSV',IUSS'UsSV',cCUrIS'UsSV';C/US,sUxUV,Us,s,UxUsU;I/USIxS'xI',UCUS'xI',cCUrIS'xI',USsCV'sSxI',USIxS'cVcSV'sSxI',UCUS'cVcSV'sSxI',cCUrIS'cVcSV'sSxI',USIxS'UsSV'sSxI',UCUS'UsSV'sSxI',cCUrIS'UsSV'sSxI';U/sCIUx,sC;S'/IS',cSS',e;V'/rV',e;I'/USS'xI',VrVUI',xUI',USS'cVcSV'sSxI',sSCV'sSxI',USS'UsSV'sSxI',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination6() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;B;T;L;H#c;l;s#S/HcBH,TBl,cTlH,SlTT,SLHcS,cTcHL;B/s,cLl,LSsTH;T/sB,HcLH,LsLHl,sL;L/HcTcL,Ls,Bs,Lc,Tl;H/cBLTS,SH,l,lBs,BH,BHcSl");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;B;T;L;H;S';L';H'#c;l;s#S/HcBHS',TBlS',cTlHS',cTcHLS';B/s,cLl,LSsTH;T/sB,HcLH,LsLHl,sL;L/HcTcLL',ssL',cLlsL',sBlL',HcLHlL',sLlL';H/cBLTSH',sBBlS'HH',ssL'sLHlBlS'HH',cLlsL'sLHlBlS'HH',sBlL'sLHlBlS'HH',sLlL'sLHlBlS'HH',sLBlS'HH',cTlHS'HH',cTcHLS'HH',lH',lBsH',sHH',cLlHH',ssL'SsTHHH',cLlsL'SsTHHH',sBlL'SsTHHH',sLlL'SsTHHH',sHcSlH',cLlHcSlH',ssL'SsTHHcSlH',cLlsL'SsTHHcSlH',sBlL'SsTHHcSlH',sLlL'SsTHHcSlH';S'/lTTS',LHcSS',e;L'/sL',SsTHsL',cL',sLHllL',e;H'/cBHS'HH',cLHBlS'HH',cTcLL'sLHlBlS'HH',cLHlL'sLHlBlS'HH',cTcLL'SsTHHH',cLHlL'SsTHHH',cTcLL'SsTHHcSlH',cLHlL'SsTHHcSlH',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination7() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;Y;M;Z;T#k;l;v#S/vT,ZTM;Y/lTMTY,YZTkM,kYk,Zl,lM,Yk;M/ZM,MTSkS,lMZ;Z/YT,kTMv,YvZ,Mk;T/ZSv,TT,vM,TYv,YvS,MZZ");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;Y;M;Z;T;Y';M';Z';T'#k;l;v#S/vT,ZTM;Y/lTMTYY',kYkY',ZlY',lMY';M/ZMM',lMZM';Z/lTMTYY'TZ',kYkY'TZ',lMY'TZ',kTMvZ',lTMTYY'vZZ',kYkY'vZZ',lMY'vZZ',lMZM'kZ';T/lTMTYY'TZ'SvT',kYkY'TZ'SvT',lMY'TZ'SvT',kTMvZ'SvT',lTMTYY'vZZ'SvT',kYkY'vZZ'SvT',lMY'vZZ'SvT',lMZM'kZ'SvT',vMT',lTMTYY'vST',kYkY'vST',lTMTYY'TZ'lY'vST',kYkY'TZ'lY'vST',lMY'TZ'lY'vST',kTMvZ'lY'vST',lTMTYY'vZZ'lY'vST',kYkY'vZZ'lY'vST',lMY'vZZ'lY'vST',lMZM'kZ'lY'vST',lMY'vST',lTMTYY'TZ'MM'ZZT',kYkY'TZ'MM'ZZT',lMY'TZ'MM'ZZT',kTMvZ'MM'ZZT',lTMTYY'vZZ'MM'ZZT',kYkY'vZZ'MM'ZZT',lMY'vZZ'MM'ZZT',lMZM'kZ'MM'ZZT',lMZM'ZZT';Y'/ZTkMY',kY',e;M'/TSkSM',e;Z'/lY'TZ',lY'vZZ',MM'kZ',e;T'/TT',YvT',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination8() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;W;C;I;V;T#a;b;p;s;z#S/zWWpT,zCS,aSWT,VpTT,TVW,zTIIV;W/WS,Wp,WVzVC,TCVb,pC;C/WTWVS,WzC,TVb,pWV,CC,WCV;I/IVS,Ts,ISSWp,IITW;V/WbTC,TWb,sW;T/CWb,WSIpW,a");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;W;C;I;V;T;W';C';I';T'#a;b;p;s;z#S/zWWpT,zCS,aSWT,VpTT,TVW,zTIIV;W/TCVbW',pCW';C/TCVbW'TWVSC',pCW'TWVSC',TCVbW'zCC',pCW'zCC',TVbC',pWVC',TCVbW'CVC',pCW'CVC';I/TsI';V/TCVbW'bTC,pCW'bTC,TWb,sW;T/pCW'TWVSC'WbT',pCW'zCC'WbT',pWVC'WbT',pCW'CVC'WbT',pCW'SIpWT',aT';W'/SW',pW',VzVCW',e;C'/CC',e;I'/VSI',SSWpI',ITWI',e;T'/CVbW'TWVSC'WbT',CVbW'zCC'WbT',VbC'WbT',CVbW'CVC'WbT',CVbW'SIpWT',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination9() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;M;Z;R;K#c;n;r#S/RMnMK,RRn,rZ,nZr;M/SS,rR,SZ;Z/MK,ZS,KRrZ,SrMRc,ZZnM;R/RS,SKMn,RMc,SZSn,cSZR,cR;K/cKR,nS");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;M;Z;R;K;Z';R'#c;n;r#S/RMnMK,RRn,rZ,nZr;M/RMnMKS,RRnS,rZS,nZrS,rR,RMnMKZ,RRnZ,rZZ,nZrZ;Z/RMnMKSKZ',RRnSKZ',rZSKZ',nZrSKZ',rRKZ',RMnMKZKZ',RRnZKZ',rZZKZ',nZrZKZ',KRrZZ',RMnMKrMRcZ',RRnrMRcZ',rZrMRcZ',nZrrMRcZ';R/rZKMnR',nZrKMnR',rZZSnR',nZrZSnR',cSZRR',cRR';K/cKR,nS;Z'/SZ',ZnMZ',e;R'/SR',MnMKKMnR',RnKMnR',McR',MnMKZSnR',RnZSnR',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination10() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;D;E;H;B;O;T#i;k;l;s#S/lB,Ss,kB,kTsEk,DSHsS,SB;D/l,Di;E/SSB,kSD,THHH,Hi,SEOB,DDDS;H/i,sTBHS,iSSs,sOk,DDT;B/BkEEH,lSBkE,BTkS,lDHiS,DH,iES;O/kE,Sl,lODi,BTHOD,BHsD,HBE;T/ODlHB,BE");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;D;E;H;B;O;T;S';D';B'#i;k;l;s#S/lBS',kBS',kTsEkS',DSHsSS';D/lD';E/lBS'SB,kBS'SB,kTsEkS'SB,lD'SHsSS'SB,kSD,THHH,Hi,lBS'EOB,kBS'EOB,kTsEkS'EOB,lD'SHsSS'EOB,lD'DDS;H/i,sTBHS,iSSs,sOk,lD'DT;B/lSBkEB',lDHiSB',lD'HB',iESB';O/kE,lBS'l,kBS'l,kTsEkS'l,lD'SHsSS'l,lODi,lSBkEB'THOD,lDHiSB'THOD,lD'HB'THOD,iESB'THOD,lSBkEB'HsD,lDHiSB'HsD,lD'HB'HsD,iESB'HsD,iBE,sTBHSBE,iSSsBE,sOkBE,lD'DTBE;T/kEDlHB,lBS'lDlHB,kBS'lDlHB,kTsEkS'lDlHB,lD'SHsSS'lDlHB,lODiDlHB,lSBkEB'THODDlHB,lDHiSB'THODDlHB,lD'HB'THODDlHB,iESB'THODDlHB,lSBkEB'HsDDlHB,lDHiSB'HsDDlHB,lD'HB'HsDDlHB,iESB'HsDDlHB,iBEDlHB,sTBHSBEDlHB,iSSsBEDlHB,sOkBEDlHB,lD'DTBEDlHB,lSBkEB'E,lDHiSB'E,lD'HB'E,iESB'E;S'/sS',BS',e;D'/iD',e;B'/kEEHB',TkSB',e", cfgLeftRecElim.toString());
	}

}