package web;

import web.fault.ServiceFaultInfo;

import javax.xml.ws.WebFault;

@WebFault(name = "MailServiceFault")
public class ServiceException extends  Exception {
    private ServiceFaultInfo serviceFaultInfo;

    public ServiceException(ServiceFaultInfo serviceFaultInfo){
        this.serviceFaultInfo = serviceFaultInfo;
    }

    public ServiceFaultInfo getServiceFaultInfo() {
        return serviceFaultInfo;
    }

    public void setServiceFaultInfo(ServiceFaultInfo serviceFaultInfo) {
        this.serviceFaultInfo = serviceFaultInfo;
    }
}
