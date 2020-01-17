module.exports = (sequelize, DataTypes) => {
  const Applicant = sequelize.define('Applicant', {
    name: {
      type: DataTypes.STRING,
      allowNull: false
    }
  });
  Applicant.associate = function(models) {
    // associations can be defined here
  };
  return Applicant;
};
