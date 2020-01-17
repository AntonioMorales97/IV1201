import ApplicantService from '../services/ApplicantService';
import Util from '../utils/Utils';

const util = new Util();

class ApplicantController {
  static async getAllApplicants(req, res) {
    try {
      const allApplicants = await ApplicantService.getAllApplicants();
      if (allApplicants.length > 0) {
        util.setSuccess(200, 'Applicants retrieved', allApplicants);
      } else {
        util.setSuccess(200, 'No applicants found');
      }
      return util.send(res);
    } catch (error) {
      util.setError(400, error);
      return util.send(res);
    }
  }

  static async addApplicant(req, res) {
    if (!req.body.name) {
      util.setError(400, 'Please enter all fields');
      return util.send(res);
    }

    const newApplicant = req.body;
    try {
      const createdApplicant = await ApplicantService.addApplicant(
        newApplicant
      );
      util.setSuccess(201, 'Applicanted added!', createdApplicant);
      return util.send(res);
    } catch (error) {
      util.setError(400, error.message);
      return util.send(res);
    }
  }
}

export default ApplicantController;
