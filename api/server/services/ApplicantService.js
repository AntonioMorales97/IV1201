import database from '../src/models';

class ApplicantService {
  static async getAllApplicants() {
    try {
      return await database.Applicant.findAll();
    } catch (error) {
      throw error;
    }
  }

  static async addApplicant(newApplicant) {
    try {
      return await database.Applicant.create(newApplicant);
    } catch (error) {
      throw error;
    }
  }
}

export default ApplicantService;
