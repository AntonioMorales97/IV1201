"use strict";

module.exports = function (sequelize, DataTypes) {
  var Applicant = sequelize.define('Applicant', {
    name: {
      type: DataTypes.STRING,
      allowNull: false
    }
  });

  Applicant.associate = function (models) {// associations can be defined here
  };

  return Applicant;
};
//# sourceMappingURL=applicant.js.map