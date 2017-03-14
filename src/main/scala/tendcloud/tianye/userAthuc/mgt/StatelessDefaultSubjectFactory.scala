package tendcloud.tianye.userAthuc.mgt

import org.apache.shiro.subject.{Subject, SubjectContext}
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory

/**
  * Created by tend on 2017/3/13.
  */
class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory{

  override def createSubject(context: SubjectContext): Subject = {
    //不创建Session
    context.setSessionCreationEnabled(false)
    super.createSubject(context)
  }
}
