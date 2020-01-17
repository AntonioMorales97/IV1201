"use strict";

var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _express = require("express");

var _ApplicantController = _interopRequireDefault(require("../controllers/ApplicantController"));

var router = (0, _express.Router)();
router.get('/', _ApplicantController["default"].getAllApplicants);
router.post('/', _ApplicantController["default"].addApplicant);
var _default = router;
exports["default"] = _default;
//# sourceMappingURL=ApplicantRoutes.js.map