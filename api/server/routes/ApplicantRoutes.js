import { Router } from 'express';
import ApplicantController from '../controllers/ApplicantController';

const router = Router();

router.get('/', ApplicantController.getAllApplicants);
router.post('/', ApplicantController.addApplicant);

export default router;
