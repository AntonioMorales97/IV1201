"use strict";

var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

var _chai = _interopRequireDefault(require("chai"));

var _chaiHttp = _interopRequireDefault(require("chai-http"));

require("chai/register-should");

var _index = _interopRequireDefault(require("../index"));

_chai["default"].use(_chaiHttp["default"]);

var expect = _chai["default"].expect;
describe('Testing Applicants endpoints:', function () {
  it('It should create an applicant', function (done) {
    var applicant = {
      name: 'Dummy'
    };

    _chai["default"].request(_index["default"]).post('/api/applicants/').set('Accept', 'application/json').send(applicant).end(function (err, res) {
      expect(res.status).to.equal(201);
      expect(res.body.data).to.include({
        id: 1,
        name: applicant.name
      });
      done();
    });
  });
  it('It should get all books', function (done) {
    _chai["default"].request(_index["default"]).get('/api/applicants/').accept('Accept', 'application/json').end(function (err, res) {
      expect(res.status).to.equal(200);
      res.body.data[0].should.have.property('id');
      res.body.data[0].should.have.property('name');
      done();
    });
  });
});
//# sourceMappingURL=test.js.map