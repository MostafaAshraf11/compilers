package csen1002.tests.task4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task4.CfgEpsUnitElim;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task4TestsBatch8 {

	@Test
	public void testCfgEpsilonRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;M;O;J;K;I;V#p;r;s;t#S/I,KM,OM,sMt;M/JpJpV,rV;O/IrItJ,Is,KJr,KpMpV;J/S,V,e,pKI;K/I,K,e,p,rOVr;I/M,O,S,pMSI,tK;V/SK,VMrO");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;M;O;J;K;I;V#p;r;s;t#S/I,KM,M,OM,sMt;M/JpJpV,JppV,pJpV,ppV,rV;O/IrIt,IrItJ,Is,Jr,KJr,KpMpV,Kr,pMpV,r;J/S,V,pI,pKI;K/I,K,p,rOVr;I/M,O,S,pMSI,t,tK;V/S,SK,VMrO", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;U;B;Y;A;V#g;k;q;x#S/BVgU,S,Y,g,qUkAB;U/A,ASg,U,Y,e,g;B/Yq,e,gS,k;Y/AxSUq,BqBU,VUgAq,YV;A/A,S,VUqU,e;V/AVx,VYY");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;U;B;Y;A;V#g;k;q;x#S/BVg,BVgU,S,Vg,VgU,Y,g,qUk,qUkA,qUkAB,qUkB,qk,qkA,qkAB,qkB;U/A,ASg,Sg,U,Y,g;B/Yq,gS,k;Y/AxSUq,AxSq,Bq,BqB,BqBU,BqU,VUgAq,VUgq,VgAq,Vgq,YV,q,qB,qBU,qU,xSUq,xSq;A/A,S,VUq,VUqU,Vq,VqU;V/AVx,VYY,Vx", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;J;T;B;M;Z;H#m;t;v#S/Jt,M,Sv,m,vSZm;J/JHv,MS,S,Z,e,mMM;T/JZH,e,tSt,vSM;B/B,BZJm,MtBMZ,S,Z,vBZ;M/B,HvMMH,Z;Z/Hv,SB,TvZtB,vM;H/HZS,ZJJB");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;J;T;B;M;Z;H#m;t;v#S/Jt,M,Sv,m,t,vSZm;J/Hv,JHv,MS,S,Z,mMM;T/JZH,ZH,tSt,vSM;B/B,BZJm,BZm,MtBMZ,S,Z,vBZ;M/B,HvMMH,Z;Z/Hv,SB,TvZtB,vM,vZtB;H/HZS,ZB,ZJB,ZJJB", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;H;R;F;P;L;M#a;c;m;w#S/LmPw,Sc,cSmLR;H/LHmF,Pw,Sa,cPS;R/R,aHaR,wLa;F/F,HaRM,c,cPcP,wHS;P/R,SPaPP;L/F,LwLH,P;M/LRc,aRMS");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;H;R;F;P;L;M#a;c;m;w#S/LmPw,Sc,cSmLR;H/LHmF,Pw,Sa,cPS;R/aHaR,wLa;F/HaRM,c,cPcP,wHS;P/SPaPP,aHaR,wLa;L/HaRM,LwLH,SPaPP,aHaR,c,cPcP,wHS,wLa;M/LRc,aRMS", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;E;Y;G;D;Q#m;p;r#S/rGmS,rSG;E/S,Y,YrDY,mE;Y/G,GpGm,GrD,YY,mGE;G/D,E,Q,SDQ,YE,rS;D/E,G,GQpS,QmEEr,p,pSYG;Q/EE,QpYDD,QrDm");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;E;Y;G;D;Q#m;p;r#S/rGmS,rSG;E/EE,GQpS,GpGm,GrD,QmEEr,QpYDD,QrDm,SDQ,YE,YY,YrDY,mE,mGE,p,pSYG,rGmS,rS,rSG;Y/EE,GQpS,GpGm,GrD,QmEEr,QpYDD,QrDm,SDQ,YE,YY,YrDY,mE,mGE,p,pSYG,rGmS,rS,rSG;G/EE,GQpS,GpGm,GrD,QmEEr,QpYDD,QrDm,SDQ,YE,YY,YrDY,mE,mGE,p,pSYG,rGmS,rS,rSG;D/EE,GQpS,GpGm,GrD,QmEEr,QpYDD,QrDm,SDQ,YE,YY,YrDY,mE,mGE,p,pSYG,rGmS,rS,rSG;Q/EE,QpYDD,QrDm", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;U;I;N;H;R;D#b;f;h;k;t#S/IhNRU,R,UhNN,tURHI;U/SNUI,tHhNI;I/N,tDUIk;N/HHS,RSt,bR;H/DRhS,I,R,S,U;R/IbNUR,N,S,U,hDbRR,hU;D/Dt,IS,fHtU");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;U;I;N;H;R;D#b;f;h;k;t#S/HHS,IbNUR,IhNRU,RSt,SNUI,UhNN,bR,hDbRR,hU,tHhNI,tURHI;U/SNUI,tHhNI;I/HHS,RSt,bR,tDUIk;N/HHS,RSt,bR;H/DRhS,HHS,IbNUR,IhNRU,RSt,SNUI,UhNN,bR,hDbRR,hU,tDUIk,tHhNI,tURHI;R/HHS,IbNUR,IhNRU,RSt,SNUI,UhNN,bR,hDbRR,hU,tHhNI,tURHI;D/Dt,IS,fHtU", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;I;L;J;V;F;R#d;h;n;t;y#S/RISJI,S,dFnJJ,yI;I/SRF,dRyFy,e,tId,tJJ;L/F,I,JS,RR,V;J/LdI,V;V/JInLy,LJLy,R,e;F/F,FhI,S,dFVFR,dIIdS,e;R/J,hIV,nSL,tSd");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;I;L;J;V;F;R#d;h;n;t;y#S/IS,ISI,ISJ,ISJI,RIS,RISI,RISJ,RISJI,RS,RSI,RSJ,RSJI,SI,SJ,SJI,dFn,dFnJ,dFnJJ,dn,dnJ,dnJJ,y,yI;I/IS,ISI,ISJ,ISJI,RIS,RISI,RISJ,RISJI,RS,RSI,RSJ,RSJI,SF,SI,SJ,SJI,SR,SRF,dFn,dFnJ,dFnJJ,dRyFy,dRyy,dn,dnJ,dnJJ,dyFy,dyy,t,tId,tJ,tJJ,td,y,yI;L/Fh,FhI,IS,ISI,ISJ,ISJI,InLy,Iny,JInLy,JIny,JLy,JS,JnLy,Jny,Jy,LJLy,LJy,LLy,Ld,LdI,Ly,RIS,RISI,RISJ,RISJI,RR,RS,RSI,RSJ,RSJI,SF,SI,SJ,SJI,SR,SRF,d,dF,dFF,dFFR,dFR,dFV,dFVF,dFVFR,dFVR,dFn,dFnJ,dFnJJ,dI,dIIdS,dIdS,dR,dRyFy,dRyy,dV,dVF,dVFR,dVR,ddS,dn,dnJ,dnJJ,dyFy,dyy,h,hI,hIV,hV,nLy,nS,nSL,ny,t,tId,tJ,tJJ,tSd,td,y,yI;J/InLy,Iny,JInLy,JIny,JLy,JnLy,Jny,Jy,LJLy,LJy,LLy,Ld,LdI,Ly,d,dI,h,hI,hIV,hV,nLy,nS,nSL,ny,tSd,y;V/InLy,Iny,JInLy,JIny,JLy,JnLy,Jny,Jy,LJLy,LJy,LLy,Ld,LdI,Ly,d,dI,h,hI,hIV,hV,nLy,nS,nSL,ny,tSd,y;F/Fh,FhI,IS,ISI,ISJ,ISJI,RIS,RISI,RISJ,RISJI,RS,RSI,RSJ,RSJI,SI,SJ,SJI,d,dF,dFF,dFFR,dFR,dFV,dFVF,dFVFR,dFVR,dFn,dFnJ,dFnJJ,dIIdS,dIdS,dR,dV,dVF,dVFR,dVR,ddS,dn,dnJ,dnJJ,h,hI,y,yI;R/InLy,Iny,JInLy,JIny,JLy,JnLy,Jny,Jy,LJLy,LJy,LLy,Ld,LdI,Ly,d,dI,h,hI,hIV,hV,nLy,nS,nSL,ny,tSd,y", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;A;X;W;F#a;g;o;r;t#S/SS,rXXrS,tArAg;A/WXgWS,aFF,e,tSWgX;X/F,S,XaSF,e,oWAW;W/A,S,SaXWW,WgS,X,e,tS;F/A,FgX,SaSFg,XF");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;A;X;W;F#a;g;o;r;t#S/SS,rXXrS,rXrS,rrS,tArAg,tArg,trAg,trg;A/WXgS,WXgWS,WgS,WgWS,XgS,XgWS,a,aF,aFF,gS,gWS,tSWg,tSWgX,tSg,tSgX;X/Fg,FgX,SS,SaSFg,SaSg,WXgS,WXgWS,WgS,WgWS,XF,XaS,XaSF,XgS,XgWS,a,aF,aFF,aS,aSF,g,gS,gWS,gX,o,oA,oAW,oW,oWA,oWAW,oWW,rXXrS,rXrS,rrS,tArAg,tArg,tSWg,tSWgX,tSg,tSgX,trAg,trg;W/Fg,FgX,SS,Sa,SaSFg,SaSg,SaW,SaWW,SaX,SaXW,SaXWW,WXgS,WXgWS,WgS,WgWS,XF,XaS,XaSF,XgS,XgWS,a,aF,aFF,aS,aSF,g,gS,gWS,gX,o,oA,oAW,oW,oWA,oWAW,oWW,rXXrS,rXrS,rrS,tArAg,tArg,tS,tSWg,tSWgX,tSg,tSgX,trAg,trg;F/Fg,FgX,SS,SaSFg,SaSg,WXgS,WXgWS,WgS,WgWS,XF,XaS,XaSF,XgS,XgWS,a,aF,aFF,aS,aSF,g,gS,gWS,gX,o,oA,oAW,oW,oWA,oWAW,oWW,rXXrS,rXrS,rrS,tArAg,tArg,tSWg,tSWgX,tSg,tSgX,trAg,trg", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;F;C;N;Q;X#h;v;z#S/F,FzXQQ,NXN,QNX,z;F/F,S,SQX;C/S,Xz,e,hS;N/C,Q,QNC,e,hSzXN,vXCQX;Q/CNXN,F,NzQF,XzNFv,e,zSzQS;X/QvF,h,vCNvC,zSNXF");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;F;C;N;Q;X#h;v;z#S/FzX,FzXQ,FzXQQ,NX,NXN,QNX,QX,QvF,SQX,SX,XN,h,vCNv,vCNvC,vCv,vCvC,vF,vNv,vNvC,vv,vvC,z,zSNXF,zSXF;F/FzX,FzXQ,FzXQQ,NX,NXN,QNX,QX,QvF,SQX,SX,XN,h,vCNv,vCNvC,vCv,vCvC,vF,vNv,vNvC,vv,vvC,z,zSNXF,zSXF;C/FzX,FzXQ,FzXQQ,NX,NXN,QNX,QX,QvF,SQX,SX,XN,Xz,h,hS,vCNv,vCNvC,vCv,vCvC,vF,vNv,vNvC,vv,vvC,z,zSNXF,zSXF;N/CNX,CNXN,CX,CXN,FzX,FzXQ,FzXQQ,NC,NX,NXN,NzF,NzQF,QC,QN,QNC,QNX,QX,QvF,SQX,SX,XN,Xz,XzFv,XzNFv,h,hS,hSzX,hSzXN,vCNv,vCNvC,vCv,vCvC,vF,vNv,vNvC,vXCQX,vXCX,vXQX,vXX,vv,vvC,z,zF,zQF,zSNXF,zSXF,zSzQS,zSzS;Q/CNX,CNXN,CX,CXN,FzX,FzXQ,FzXQQ,NX,NXN,NzF,NzQF,QNX,QX,QvF,SQX,SX,XN,XzFv,XzNFv,h,vCNv,vCNvC,vCv,vCvC,vF,vNv,vNvC,vv,vvC,z,zF,zQF,zSNXF,zSXF,zSzQS,zSzS;X/QvF,h,vCNv,vCNvC,vCv,vCvC,vF,vNv,vNvC,vv,vvC,zSNXF,zSXF", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination4() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;J;B;C;N;R#g;q;w;x#S/Cg,NBgC,qNBJ,qRJwN;J/BRNNw,CNJx,JC,S,e,wNCR;B/B,RwNx,e,gBx;C/NxBxJ,gSwS,qNxCS;N/B,C,CN,SCxJ,e,gCCqS,xC;R/CS,J,NwSqS,S");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;J;B;C;N;R#g;q;w;x#S/BgC,Cg,NBgC,NgC,gC,q,qB,qBJ,qJ,qJw,qJwN,qN,qNB,qNBJ,qNJ,qRJw,qRJwN,qRw,qRwN,qw,qwN;J/BNNw,BNw,BRNNw,BRNw,BRw,BgC,Bw,CJx,CNJx,CNx,Cg,Cx,JC,NBgC,NNw,NgC,Nw,NxBx,NxBxJ,Nxx,NxxJ,RNNw,RNw,Rw,gC,gSwS,q,qB,qBJ,qJ,qJw,qJwN,qN,qNB,qNBJ,qNJ,qNxCS,qRJw,qRJwN,qRw,qRwN,qw,qwN,qxCS,w,wC,wCR,wNC,wNCR,xBx,xBxJ,xx,xxJ;B/RwNx,Rwx,gBx,gx,wNx,wx;C/NxBx,NxBxJ,Nxx,NxxJ,gSwS,qNxCS,qxCS,xBx,xBxJ,xx,xxJ;N/CN,NxBx,NxBxJ,Nxx,NxxJ,RwNx,Rwx,SCx,SCxJ,gBx,gCCqS,gSwS,gx,qNxCS,qxCS,wNx,wx,xBx,xBxJ,xC,xx,xxJ;R/BNNw,BNw,BRNNw,BRNw,BRw,BgC,Bw,CJx,CNJx,CNx,CS,Cg,Cx,JC,NBgC,NNw,NgC,Nw,NwSqS,NxBx,NxBxJ,Nxx,NxxJ,RNNw,RNw,Rw,gC,gSwS,q,qB,qBJ,qJ,qJw,qJwN,qN,qNB,qNBJ,qNJ,qNxCS,qRJw,qRJwN,qRw,qRwN,qw,qwN,qxCS,w,wC,wCR,wNC,wNCR,wSqS,xBx,xBxJ,xx,xxJ", cfgEpsUnitElim.toString());
	}

}