"use strict";

var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _regenerator = _interopRequireDefault(require("@babel/runtime/regenerator"));

var _asyncToGenerator2 = _interopRequireDefault(require("@babel/runtime/helpers/asyncToGenerator"));

var _classCallCheck2 = _interopRequireDefault(require("@babel/runtime/helpers/classCallCheck"));

var _createClass2 = _interopRequireDefault(require("@babel/runtime/helpers/createClass"));

var _ApplicantService = _interopRequireDefault(require("../services/ApplicantService"));

var _Utils = _interopRequireDefault(require("../utils/Utils"));

var util = new _Utils["default"]();

var ApplicantController =
/*#__PURE__*/
function () {
  function ApplicantController() {
    (0, _classCallCheck2["default"])(this, ApplicantController);
  }

  (0, _createClass2["default"])(ApplicantController, null, [{
    key: "getAllApplicants",
    value: function () {
      var _getAllApplicants = (0, _asyncToGenerator2["default"])(
      /*#__PURE__*/
      _regenerator["default"].mark(function _callee(req, res) {
        var allApplicants;
        return _regenerator["default"].wrap(function _callee$(_context) {
          while (1) {
            switch (_context.prev = _context.next) {
              case 0:
                _context.prev = 0;
                _context.next = 3;
                return _ApplicantService["default"].getAllApplicants();

              case 3:
                allApplicants = _context.sent;

                if (allApplicants.length > 0) {
                  util.setSuccess(200, 'Applicants retrieved', allApplicants);
                } else {
                  util.setSuccess(200, 'No applicants found');
                }

                return _context.abrupt("return", util.send(res));

              case 8:
                _context.prev = 8;
                _context.t0 = _context["catch"](0);
                util.setError(400, _context.t0);
                return _context.abrupt("return", util.send(res));

              case 12:
              case "end":
                return _context.stop();
            }
          }
        }, _callee, null, [[0, 8]]);
      }));

      function getAllApplicants(_x, _x2) {
        return _getAllApplicants.apply(this, arguments);
      }

      return getAllApplicants;
    }()
  }, {
    key: "addApplicant",
    value: function () {
      var _addApplicant = (0, _asyncToGenerator2["default"])(
      /*#__PURE__*/
      _regenerator["default"].mark(function _callee2(req, res) {
        var newApplicant, createdApplicant;
        return _regenerator["default"].wrap(function _callee2$(_context2) {
          while (1) {
            switch (_context2.prev = _context2.next) {
              case 0:
                if (req.body.name) {
                  _context2.next = 3;
                  break;
                }

                util.setError(400, 'Please enter all fields');
                return _context2.abrupt("return", util.send(res));

              case 3:
                newApplicant = req.body;
                _context2.prev = 4;
                _context2.next = 7;
                return _ApplicantService["default"].addApplicant(newApplicant);

              case 7:
                createdApplicant = _context2.sent;
                util.setSuccess(201, 'Applicanted added!', createdApplicant);
                return _context2.abrupt("return", util.send(res));

              case 12:
                _context2.prev = 12;
                _context2.t0 = _context2["catch"](4);
                util.setError(400, _context2.t0.message);
                return _context2.abrupt("return", util.send(res));

              case 16:
              case "end":
                return _context2.stop();
            }
          }
        }, _callee2, null, [[4, 12]]);
      }));

      function addApplicant(_x3, _x4) {
        return _addApplicant.apply(this, arguments);
      }

      return addApplicant;
    }()
  }]);
  return ApplicantController;
}();

var _default = ApplicantController;
exports["default"] = _default;
//# sourceMappingURL=ApplicantController.js.map