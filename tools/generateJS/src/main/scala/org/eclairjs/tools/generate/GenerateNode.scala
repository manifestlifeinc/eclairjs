package org.eclairjs.tools.generate

import org.eclairjs.tools.generate.model._

class GenerateNode  extends  GenerateJSBase {



  override def generateConstructor(cls:Clazz, sb:StringBuilder): Unit = {
    val clsName=cls.name
    var parmlist=""
    var constrBody=""

    val constructor=mainConstructor(cls);

    if (constructor!=null)
      {
         parmlist=constructor.parmList();
        if (parmlist.length>0)
          parmlist=", "+parmlist


         constrBody=constructor.parms.map(parm=> s"  this.${parm.name} = ${parm.name}").mkString("/n")

      }

    val constr = getTemplate("node_constructorDefault",clsName,parmlist,constrBody)

    sb++=constr

  }
  override def generateObject(cls:Clazz, sb:StringBuilder): Unit={}

  override def generatePostlude(cls:Clazz, sb:StringBuilder): Unit= {
    val clsName=cls.name

    val constr = getTemplate("node_postlude",clsName)

    sb++=constr

  }

  override def generateIncludes(file:File, sb:StringBuilder): Unit = {

    // determine path to root directory
    val rootDir="apache/spark/"
    val inx=file.fileName.indexOf(rootDir)
    val segments=file.fileName.substring(inx+rootDir.length).split("/").length-1
    val prefix= if (segments==0) "."
    else
      {
        List("..","..","..","..","..","..","..","..","..","..","..","..").take(segments).mkString("/")
      }
    val constr = getTemplate("node_defaultRequires",prefix,prefix)

    sb++=constr
  }


  def getMethodBody(method:Method): String =
  {
    ""
  }

}
